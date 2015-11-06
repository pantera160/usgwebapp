var app = angular.module('Login');
app.factory('Base64', function() {
	var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
	return {
		encode: function(input) {
			var output = "";
			var chr1, chr2, chr3 = "";
			var enc1, enc2, enc3, enc4 = "";
			var i = 0;
			do {
				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);
				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;
				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}
				output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + keyStr.charAt(enc3) + keyStr.charAt(enc4);
				chr1 = chr2 = chr3 = "";
				enc1 = enc2 = enc3 = enc4 = "";
			} while (i < input.length);
			return output;
		},
		decode: function(input) {
			var output = "";
			var chr1, chr2, chr3 = "";
			var enc1, enc2, enc3, enc4 = "";
			var i = 0;
			// remove all characters that are not A-Z, a-z, 0-9, +, /, or =
			var base64test = /[^A-Za-z0-9\+\/\=]/g;
			if (base64test.exec(input)) {
				window.alert("There were invalid base64 characters in the input text.\n" + "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" + "Expect errors in decoding.");
			}
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			do {
				enc1 = keyStr.indexOf(input.charAt(i++));
				enc2 = keyStr.indexOf(input.charAt(i++));
				enc3 = keyStr.indexOf(input.charAt(i++));
				enc4 = keyStr.indexOf(input.charAt(i++));
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
				output = output + String.fromCharCode(chr1);
				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}
				chr1 = chr2 = chr3 = "";
				enc1 = enc2 = enc3 = enc4 = "";
			} while (i < input.length);
			return output;
		}
	};
});
app.service('AuthenticationService', ['$http', 'Base64', '$rootScope', '$cookieStore', function($http, Base64, $rootScope, $cookieStore) {
	this.Login = function($data) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/crm/login',
			data: $data
		});
	};
	this.newPass = function(userid, pass) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'PUT',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/crm/passchange',
			data: {
				userid: userid,
				pass: pass
			}
		});
	};
	this.SetCredentials = function(username, password, accessLvl, userid) {
		var authdata = Base64.encode(username + ':' + password);
		$rootScope.globals = {
			currentUser: {
				username: username,
				authdata: authdata,
				accessLvl: accessLvl,
				userid: userid
			}
		};
		$rootScope.loggedin = true;
		$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
		$cookieStore.put('globals', $rootScope.globals);
	};
	this.ClearCredentials = function() {
		$rootScope.globals = {};
		$cookieStore.remove('globals');
		$http.defaults.headers.common.Authorization = 'Basic ';
		$rootScope.loggedin = false;
	};
}]);
app.controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService', function($scope, $rootScope, $location, AuthenticationService) {
	// reset login status
	AuthenticationService.ClearCredentials();
	$scope.login = function() {
		$scope.dataLoading = true;
		var data = {
			'username': $scope.username,
			'password': $scope.password
		};
		AuthenticationService.Login(data).then(function(response) {
			if (response.data.succes) {
				console.log("new_pass=" + response.data.new_pass);
				if (response.data.new_pass == '1') {
					console.log("changepass");
					$rootScope.globals = {
						currentUser: {
							userid: response.data.userid
						}
					};
					$location.path('/firstlogin');
				} else {
					AuthenticationService.SetCredentials($scope.username, $scope.password, response.data.privilegelvl, response.data.userid);
					$location.path('/overview');
				}
			} else {
				$scope.error = response.data.message;
				$scope.dataLoading = false;
			}
		});
	};
}]);
app.directive('compareTo', function() {
	return {
		require: 'ngModel',
		scope: {
			otherModelValue: "=compareTo"
		},
		link: function(scope, element, attributes, ngModel) {
			ngModel.$validators.compareTo = function(modelValue) {
				return modelValue == scope.otherModelValue;
			};
			scope.$watch("otherModelValue", function() {
				ngModel.$validate();
			});
		}
	};
});
app.controller('NewPassCtrl', ['$scope', 'AuthenticationService', '$rootScope', function($scope, AuthenticationService, $rootScope) {
	$scope.password = "";
	var userid = $rootScope.globals.currentUser.userid;
	$scope.changePass = function() {
		AuthenticationService.newPass(userid, $scope.password).then(function(response) {
			if (response.data.newpass === 0) {
				AuthenticationService.SetCredentials(response.data.username, $scope.password, response.data.privilegelvl, response.data.userid);
				$location.path('/overview');
			}
		});
	};
}]);