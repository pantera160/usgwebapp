var app = angular.module("BalansRatio");
app.service('BalansService', function($http) {
	this.getData = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/data/balans/'+$id
		});
	};
	this.getCompany = function($id){
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://wcstool-usg.rhcloud.com/rest/data/company/'+$id
		});
	};
});

app.service('helpServiceBalans', function(ngDialog) {
	this.showHelp = function(controller) {
		console.log('showHelp called');
		if (angular.equals(controller, 'solvency')) {
			ngDialog.open({
				template: 'solvencytemplate',
				className: 'ngdialog-theme-default'
			});
		} else if (angular.equals(controller, 'currratio')) {
			ngDialog.open({
				template: 'currratiotemplate',
				className: 'ngdialog-theme-default'
			});
		} else if (angular.equals(controller, 'quickratio')) {
			ngDialog.open({
				template: 'quickratiotemplate',
				className: 'ngdialog-theme-default'
			});
		} else{
			console.log('Helptype not found');
		}
	};
});

app.controller('headerCtrl', ['$scope', 'BalansService', '$rootScope', function($scope, BalansService, $rootScope){
	$scope.company = {};
	BalansService.getCompany($rootScope.globals.currentUser.companyid).then(function(result){
		$scope.company.name = result.data.company;
		$scope.company.sector = result.data.sector;
	})
}]);

app.controller('SolvencyCtrl', ['$scope', 'BalansService', 'helpServiceBalans', '$rootScope', function($scope, BalansService, helpServiceBalans, $rootScope) {
	$scope.help = function() {
		console.log('help called');
		helpServiceBalans.showHelp('solvency');
	};
	$scope.data = [
		[]
	];
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	$scope.labels = [];
	BalansService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if (parseInt($value.year) > 0) {
				$scope.result = result.data;
				$scope.data[0].push(round($value.solvency * 100));
				$scope.labels.push($value.year);
				colourArray.push("#AB7B0C");
			} else {
				$scope.data[0].unshift(round($value.solvency));
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{
			fillColor: colourArray
		}];
	});
	$scope.options = {
		scaleLabel: function(valuePayload) {
			return Number(valuePayload.value).toFixed(1) + '%';
		},
		scaleShowVerticalLines: false
	};
	$scope.condition = function(item){
		if(item >= 28.5){
			return "IMG/green.png";
		}
		else if(item >= 25.5){
			return "IMG/orange.png";
		}
		else{
			return "IMG/red.png";
		}
	}
}]);
app.controller('CurrRatioCtrl', ['$scope', 'BalansService', 'helpServiceBalans', '$rootScope', function($scope, BalansService, helpServiceBalans, $rootScope) {
	$scope.help = function() {
		helpServiceBalans.showHelp('currratio');
	};
	$scope.data = [
		[]
	];
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	$scope.labels = [];
	BalansService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if (parseInt($value.year) > 0) {
				$scope.data[0].push(round($value.currRatio * 100));
				$scope.labels.push($value.year);
				colourArray.push("#AB7B0C");
			} else {
				$scope.data[0].unshift(round($value.currRatio));
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{
			fillColor: colourArray
		}];
	});
	$scope.options = {
		scaleLabel: function(valuePayload) {
			return Number(valuePayload.value).toFixed(1) + '%';
		},
		scaleShowVerticalLines: false
	};
	$scope.condition = function(item){
		if(item >= 118.75){
			return "IMG/green.png";
		}
		else if(item >= 106.25){
			return "IMG/orange.png";
		}
		else{
			return "IMG/red.png";
		}
	};
}]);
app.controller('QuickRatioCtrl', ['$scope', 'BalansService', 'helpServiceBalans', '$rootScope', function($scope, BalansService, helpServiceBalans, $rootScope) {
	$scope.help = function() {
		helpServiceBalans.showHelp('quickratio');
	};
	$scope.data = [
		[]
	];
	//Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
	//Edited chart.js 2138, 2141, 2142.
	var colourArray = ["#6AFEB7"];
	$scope.labels = [];
	BalansService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
		angular.forEach(result.data, function($value) {
			if (parseInt($value.year) > 0) {
				$scope.data[0].push(round($value.quickRatio * 100));
				$scope.labels.push($value.year);
				colourArray.push("#AB7B0C");
			} else {
				$scope.data[0].unshift(round($value.quickRatio));
				$scope.labels.unshift("Sector Average");
			}
		});
		//Add colours array to correct syntax for charts
		$scope.colours = [{
			fillColor: colourArray
		}];
	});
	$scope.options = {
		scaleLabel: function(valuePayload) {
			return Number(valuePayload.value).toFixed(1) + '%';
		},
		scaleShowVerticalLines: false
	};
	$scope.condition = function(item){
		if(item >= 95){
			return "IMG/green.png";
		}
		else if(item >= 85){
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
var round = function(n) {
		return Math.round(n * 10) / 10;
	}