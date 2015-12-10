var app = angular.module("ReturnRatio")
app.service('ReturnService', function($http) {
	this.getData = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/data/return/'+$id,
			cache : true
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
		if(angular.equals(controller, 'equity')){
			ngDialog.open({
				template : 'equitytemplate', className: 'ngdialog-theme-default'
			});
		}
		else if(angular.equals(controller, 'assets')){
			ngDialog.open({
				template : 'assetstemplate', className: 'ngdialog-theme-default'
			});
		}
	};
});

app.controller('headerCtrl', ['$scope', 'ReturnService', '$rootScope', function($scope, ReturnService, $rootScope){
	$scope.company = {};
	ReturnService.getCompany($rootScope.globals.currentUser.companyid).then(function(result){
		$scope.company.name = result.data.company;
		$scope.company.sector = result.data.sector;
	})
}]);

app.controller('EquityCtrl', ['$scope', 'ReturnService', 'helpService', '$rootScope', function($scope, ReturnService, helpService, $rootScope) {
	$scope.help = function(){
		console.log('help called');
		helpService.showHelp('equity');
	};
	$scope.options = {
		scaleShowVerticalLines: false
	};
	var sectorAvgE;
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	$scope.data = [[]];
	$scope.labels = [];
	ReturnService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if(parseInt($value.year) > 0){
				$scope.data[0].push(round($value.roe*100));
				$scope.labels.push($value.year);
				colourArray.push("#AB7B0C");
			}
			else{
				$scope.data[0].unshift(round($value.roe*100));
				sectorAvgE = round($value.roe*100);
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{fillColor: colourArray}];
	});

	$scope.options = {
		scaleLabel: function(valuePayload) {
			return Number(valuePayload.value).toFixed(1) + '%';
		}
	};
	$scope.condition = function(item){
		if(item >= (sectorAvgE*0.95)){
			return "IMG/green.png";
		}
		else if(item >= (sectorAvgE*0.85)){
			return "IMG/orange.png";
		}
		else{
			return "IMG/red.png";
		}
	};
}]);
app.controller('AssetsCtrl', ['$scope', 'ReturnService', 'helpService', '$rootScope', function($scope, ReturnService, helpService, $rootScope) {
	$scope.help = function(){
		console.log('help called');
		helpService.showHelp('assets');
	};
	$scope.options = {
		scaleShowVerticalLines: false
	};
	$scope.data = [[]];
	$scope.labels = [];
	var sectorAvgA;
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	ReturnService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if($value.year > 0){
				$scope.data[0].push(round($value.roa*100));
				$scope.labels.push($value.year);
				colourArray.push("#AB7B0C");
			}
			else{
				$scope.data[0].unshift(round($value.roa*100));
				sectorAvgA = round($value.roa*100);
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{fillColor: colourArray}];
	});

	$scope.options = {
		scaleLabel: function(valuePayload) {
			return Number(valuePayload.value).toFixed(1) + '%';
		}
	};
	$scope.condition = function(item){
		if(item >= (sectorAvgA*0.95)){
			return "IMG/green.png";
		}
		else if(item >= (sectorAvgA*0.85)){
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
		$location.path("portal.html");
	};
});

var round = function(n){
	return Math.round(n * 10) / 10;
}