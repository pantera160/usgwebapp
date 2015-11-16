var app = angular.module("Admin");
app.service('adminService', function($http) {
	this.getUsers = function() {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/crm/users'
		});
	}
	this.resetMail = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'PUT',
			url: 'http://wcstool-usg.rhcloud.com/rest/crm/user/'+$id
		});
	}
	this.deleteUser = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'DELETE',
			url: 'http://wcstool-usg.rhcloud.com/rest/crm/user/'+$id
		});
	}
	this.newuser = function($name, $email){
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'POST',
			url: 'http://wcstool-usg.rhcloud.com/rest/crm/newuser',
			data: {username : $name, email: $email}
		});
	}
});

app.controller('adminCtrl', function($scope, adminService, $timeout, $location){
	$scope.users = [];
	$scope.deleting = false;
	var getusers = function(){
		adminService.getUsers().then(function(result){
			$scope.users = result.data;
		});
	}
	
	$scope.deleteUser = function(id){
		adminService.deleteUser(id).then(function(result){
			if(result.data = 1){
			$scope.succes = true,
			$scope.text = "User has been succesfully removed.",
			$timeout(function(){
				$scope.succes = false;
			}, 3000);
			} else {
				$scope.error = true;
				$scope.text = "Something went wrong when deleting the user. Please reload and try again or contact support."
				$timeout(function(){
				$scope.error = false;
			}, 3000);
			}
		});
		getusers();
	};
	
	$scope.reset = function(id){
		adminService.resetMail(id).then(function(result){
			$scope.succes = true;
			$scope.text = "Password from user "+result.data.name+" has been succesfully reset. \n A mail with the new password is send to the email adress linked to this user.";
			$timeout(function(){
				$scope.succes = false;
			}, 3000);
		});
	}
	
	$scope.back = function(){
		$location.path('/overview');
	}
	
	$scope.add = function(){
		$location.path('/newuser');
	}
	
	$scope.newuser = function(){
		adminService.newuser($scope.username, $scope.email).then(function(result){
			if(result.data.succes == 1){
				$scope.succes = true;
				$scope.text = "User succesfully added.";
				$timeout(function(){
					$scope.succes = false;
					$location.path('/admin');
				}, 3000);
			}
			else{
				$scope.error = true;
				$scope.text = result.data.error;
			}
		});
	}
	
	getusers();
});