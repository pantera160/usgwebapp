var app = angular.module('Portal');

app.controller('PortalCtrl', function($scope, $location){
	
	$scope.back = function(){
		$location.path('/inputs');
	};
	
	$scope.route = function(path){
		$location.path('/'+path);
	};
});