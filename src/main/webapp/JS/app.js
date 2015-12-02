angular.module("USGFinanceWebapp", ['720kb.tooltips', 'ngDialog']);
angular.module("BalansRatio", ['chart.js', 'ngDialog']);
angular.module("Portal", []);
angular.module("ReturnRatio", ['chart.js', 'ngDialog']);
angular.module("CCCEvolution", ['chart.js', 'ngDialog']);
angular.module("CoverageRatio", ['chart.js', 'ngDialog']);
angular.module("TurnoverRatio", ['chart.js', 'ngDialog']);
angular.module("WCMSavings", []);
angular.module("Login", []);
angular.module('Overview', []);
angular.module("Admin", []);
angular.module('Sectors', []);
var app = angular.module('Router', ['ngRoute', 'ngCookies', 'USGFinanceWebapp', 'BalansRatio', 'Portal', 'ReturnRatio', 'CCCEvolution', 'CoverageRatio', 'TurnoverRatio', 'WCMSavings', 'Login', 'Overview', 'Admin']);
app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/inputs', {
		templateUrl: 'inputs.html'
	}).when('/balans', {
		templateUrl: 'balans.html'
	}).when('/portal', {
		templateUrl: 'portal.html'
	}).when('/return', {
		templateUrl: 'return.html'
	}).when('/coverage', {
		templateUrl: 'coverage.html'
	}).when('/turnover', {
		templateUrl: 'turnover.html'
	}).when('/wcmsavings', {
		templateUrl: 'wcmsavings.html'
	}).when('/cccevolution', {
		templateUrl: 'cccevolution.html'
	}).when('/login', {
		templateUrl: 'login.html'
	}).when('/overview', {
		templateUrl: 'overview.html'
	}).when('/admin', {
		templateUrl: 'admin.html'
	}).when('/newuser', {
		templateUrl: 'newuser.html'
	}).when('/firstlogin', {
		templateUrl: 'changepass.html'
	}).otherwise({
		redirectTo: '/overview'
	});
}]);
app.directive('myNumberformat', function() {
	/**
	 * format(number, n, x, s, c)
	 *
	 * @param Number number: number to format
	 * @param integer n: length of decimal
	 * @param integer x: length of whole part
	 * @param mixed   s: sections delimiter
	 * @param mixed   c: decimal delimiter
	 */
	var format = function(number, n, x, s, c) {
			var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
				num = Number(number).toFixed(Math.max(0, ~~n));
			return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
		}
	var validNumber = function(number) {
			if(number.indexOf(',') > -1){
				var number2 = number.replace('.', '');
				return number2.replace(',', '.');
			}
			return number.replace(',', '.');
		}
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, ngModelController) {
			ngModelController.$parsers.push(function(data) {
				//convert data from view format to model format
				return validNumber(data); //converted
			});
			ngModelController.$formatters.push(function(data) {
				//convert data from model format to view format
				return format(data, 1, 3, '.', ','); //converted
			});
		}
	};
});
app.directive('myNumberformat2', function() {
	/**
	 * format(number, n, x, s, c)
	 *
	 * @param Number number: number to format
	 * @param integer n: length of decimal
	 * @param integer x: length of whole part
	 * @param mixed   s: sections delimiter
	 * @param mixed   c: decimal delimiter
	 */
	var format = function(number, n, x, s, c) {
			var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
				num = Number(number).toFixed(Math.max(0, ~~n));
			return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
		}
	var validNumber = function(number) {
		if(number.indexOf(',') > -1){
				var number2 = number.replace('.', '');
				return number2.replace(',', '.');
			}
			return number.replace(',', '.');
		}
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, ngModelController) {
			ngModelController.$parsers.push(function(data) {
				//convert data from view format to model format
				return validNumber(data); //converted
			});
			ngModelController.$formatters.push(function(data) {
				//convert data from model format to view format
				return format(data, 2, 3, '.', ','); //converted
			});
		}
	};
});
app.filter('NumberEU', function() {
	/**
	 * Number.prototype.format(n, x, s, c)
	 *
	 * @param integer n: length of decimal
	 * @param integer x: length of whole part
	 * @param mixed   s: sections delimiter
	 * @param mixed   c: decimal delimiter
	 */
	Number.prototype.format = function(n, x, s, c) {
		var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
			num = this.toFixed(Math.max(0, ~~n));
		return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
	};
	return function(input, para1) {
		var out = Number(input).format(1, 3, '.', ',');
		return out;
	}
});
app.filter('NumberEU2', function() {
	/**
	 * Number.prototype.format(n, x, s, c)
	 *
	 * @param integer n: length of decimal
	 * @param integer x: length of whole part
	 * @param mixed   s: sections delimiter
	 * @param mixed   c: decimal delimiter
	 */
	Number.prototype.format = function(n, x, s, c) {
		var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
			num = this.toFixed(Math.max(0, ~~n));
		return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
	};
	return function(input, para1) {
		var out = Number(input).format(2, 3, '.', ',');
		return out;
	}
});
app.run(['$rootScope', '$location', '$cookieStore', '$http', function($rootScope, $location, $cookieStore, $http) {
	// keep user logged in after page refresh
	$rootScope.globals = $cookieStore.get('globals') || {};
	if ($rootScope.globals.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
		$rootScope.loggedin = true;
	}
	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		// redirect to login page if not logged in
		if($location.path() == '/login'){
			
		} else if ($location.path() !== '/login' && (!$rootScope.globals.currentUser)) {
			$location.path('/login');
		} else if($location.path() !== '/firstlogin' && !$rootScope.globals.currentUser.accessLvl){
			$location.path('/login');
		} else if ($location.path() == '/admin' && !($rootScope.globals.currentUser.accessLvl > 2)) {
			$location.path('/overview');
		}
	});
}]);
app.controller('LogoutCtrl', ['AuthenticationService', '$scope', '$rootScope', '$location', function(AuthenticationService, $scope, $rootScope, $location) {
	var checkAdmin = function(){
	if($rootScope.loggedin){
		$scope.adminAccess = $rootScope.globals.currentUser.accessLvl > 2;
	}
	else{
		$scope.adminAccess = false;
	}
	};
	$scope.logout = function() {
		AuthenticationService.ClearCredentials();
		$location.path("/login");
	}
	$scope.isLoggedin = function() {
		checkAdmin();
		return $rootScope.loggedin;
	}
	$scope.back = function() {
		$location.path("/overview")
	}
	$scope.admin = function() {
		$location.path('/admin');
	}
}]);