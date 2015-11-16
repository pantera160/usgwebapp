var app = angular.module('Portal');

app.controller('PortalCtrl', function($scope, $location){
	
	$scope.back = function(){
		$location.path('/input');
	};
	
	$scope.route = function(path){
		$location.path('/'+path);
	};
});