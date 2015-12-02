var app = angular.module("CCCEvolution");
app.service('CCCEService', function($http) {
	this.getData = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/data/turnover/'+$id
		});
	};
	this.getCompany = function(){
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/data/company/'+$id,
			cache : true
		});
	};
});

app.service('helpService', function(ngDialog){
	this.showHelp = function(controller){
		console.log('showHelp called');
		ngDialog.open({
			template : 'cccetemplate', className: 'ngdialog-theme-default'
		});
	};
});

app.controller('headerCtrl', ['$scope', 'CCCEService', '$rootScope', function($scope, CCCEService, $rootScope){
	$scope.company = {};
	CCCEService.getCompany($rootScope.globals.currentUser.companyid).then(function(result){
		$scope.company.name = result.data.company;
		$scope.company.sector = result.data.sector;
	})
}]);

app.controller('CCCECtrl', ['$scope', 'CCCEService', 'helpService', '$rootScope', function($scope, CCCEService, helpService, $rootScope) {
	$scope.help = function(){
		helpService.showHelp('ccce');
	}
	$scope.options = {
		scaleShowVerticalLines: false
	};
	$scope.data = [[]];
	$scope.labels = [];
	var sectorAvg;
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	CCCEService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if(parseInt($value.year) > 0){
			$scope.data[0].push(round($value.dio + $value.dso - $value.dpo));
			$scope.labels.push($value.year);
			colourArray.push("#AB7B0C");
			}
			else{
				$scope.data[0].unshift(round($value.dio + $value.dso - $value.dpo));
				sectorAvg = round($value.dio + $value.dso - $value.dpo);
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{fillColor: colourArray}];
	});

	$scope.options = {};
	$scope.condition = function(item){
		if(item >= (sectorAvg*0.95)){
			return "IMG/green.png";
		}
		else if(item >= (sectorAvg*0.85)){
			return "IMG/orange.png";
		}
		else{
			return "IMG/red.png";
		}
	};
}]);
app.controller('ButtonsCtrl', function($scope, $location) {
	//redirect to portal
	$scope.back = function() {
		$location.path("/portal");
	};
});

var round = function(n){
	return Math.round(n * 10) / 10;
}