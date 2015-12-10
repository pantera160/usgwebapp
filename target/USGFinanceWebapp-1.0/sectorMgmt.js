var app = angular.module('Sectors');

app.service('SectorService', function($http) {
	this.getData = function() {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/sectoroverview',
			cache : true
		});
	};
	this.getSources = function() {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/sources',
			cache : true
		});
	};
});

app.controller('sectorCtrl', ['SectorService', '$scope', function(SectorService, $scope){
	var getsectors = function(){
		SectorService.getData().then(function(result){
			$scope.sectors = result.data;
		});
	};
	
	var getsources = function(){
		SectorService.getSources().then(function(result){
			$scope.sources = result.data;
		});
	};
	
	getsectors();
	getsources();
}]);