var app = angular.module("USGFinanceWebapp");
app.service('dataService', function($http) {
	this.getData = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/input/'+$id
		});
	}
	this.saveData = function($data, $id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/input/'+$id,
			data: $data
		});
	}
	this.getCompData = function($id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/company/'+$id
		});
	}
	this.saveCompData = function($data, $id) {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/company/'+$id,
			data: $data
		});
	}
	this.getSectors = function() {
		// $http() returns a $promise that we can add handlers with .then()
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/USGFinanceWebapp/rest/data/sectors'
		});
	}
});

app.controller("HelpCtrl", function($scope, ngDialog) {
	$scope.help = function() {
		ngDialog.open({
			template: 'helptemplate',
			className: 'ngdialog-theme-default'
		});
	}
});
app.controller("MyForm", function($scope, dataService, $location, $rootScope, $cookieStore) {
	var startyear = new Date().getFullYear();
	var nextyear = new Date().getFullYear() - 1;
	$scope.data = null;
	$scope.sectors = [];
	$scope.company = {
		sector: "0"
	};
	var company_id = $rootScope.globals.currentUser.companyid;
	var userId = $rootScope.globals.currentUser.userid;
	//list of input fields
	//each data element will be linked to its input/label field to save data automatically 
	$scope.inputs = {
		"numberOfMonths": [{
			"data": "0",
			"year": startyear
		}],
		"fixedAssets": [{
			"data": "0",
			"year": startyear
		}],
		"intangiblesAssets": [{
			"data": "0",
			"year": startyear
		}],
		"propertyAssets": [{
			"data": "0",
			"year": startyear
		}],
		"finFixedAssets": [{
			"data": "0",
			"year": startyear
		}],
		"inventory": [{
			"data": "0",
			"year": startyear
		}],
		"ar": [{
			"data": "0",
			"year": startyear
		}],
		"cash": [{
			"data": "0",
			"year": startyear
		}],
		"currAssets": [{
			"data": "0",
			"year": startyear
		}],
		"totAssets": [{
			"data": "0",
			"year": startyear
		}],
		"equity": [{
			"data": "0",
			"year": startyear
		}],
		"ltFinDebt": [{
			"data": "0",
			"year": startyear
		}],
		"subordinatedDebt": [{
			"data": 0,
			"year": startyear
		}],
		"stFinDebt": [{
			"data": "0",
			"year": startyear
		}],
		"ap": [{
			"data": "0",
			"year": startyear
		}],
		"currLiabilities": [{
			"data": "0",
			"year": startyear
		}],
		"workingCapital": [{
			"data": "0",
			"year": startyear
		}],
		"netDebt": [{
			"data": "0",
			"year": startyear
		}],
		"turnover": [{
			"data": "0",
			"year": startyear
		}],
		"costOfSales": [{
			"data": "0",
			"year": startyear
		}],
		"depreciation": [{
			"data": "0",
			"year": startyear
		}],
		"ebit": [{
			"data": "0",
			"year": startyear
		}],
		"finRev": [{
			"data": "0",
			"year": startyear
		}],
		"finExp": [{
			"data": "0",
			"year": startyear
		}],
		"finExpInterest": [{
			"data": "0",
			"year": startyear
		}],
		"finExpBank": [{
			"data": "0",
			"year": startyear
		}],
		"finExpOther": [{
			"data": "0",
			"year": startyear
		}],
		"nrIncome": [{
			"data": "0",
			"year": startyear
		}],
		"nrCharges": [{
			"data": "0",
			"year": startyear
		}],
		"taxes": [{
			"data": "0",
			"year": startyear
		}],
		"recIncome": [{
			"data": "0",
			"year": startyear
		}],
		"netIncome": [{
			"data": "0",
			"year": startyear
		}],
		"ebitda": [{
			"data": "0",
			"year": startyear
		}]
	};
	//List of available currencies
	$scope.currencies = [{
		id: '1',
		name: "EUR"
	}, {
		id: '2',
		name: "GBP"
	}, {
		id: '3',
		name: "USD"
	}, {
		id: '4',
		name: 'CHF'
	}, {
		id: '5',
		name: 'DKK'
	}, {
		id: '6',
		name: 'SEK'
	}, {
		id: '7',
		name: 'NOK'
	}];
	//Set default currency
	$scope.currency = {
		id: "1",
		name: "EUR"
	};
	//list of years
	//used to generate the headers
	$scope.years = [
	startyear];
	$scope.add = function() {
		//for each row add an extra column
		angular.forEach($scope.inputs, function($value) {
			$value.push({
				"data": "0",
				"year": nextyear
			});
		});
		//add extra year to header
		$scope.years.push(
		nextyear);
		nextyear--;
	};
	//Save the input data to server
	$scope.processForm = function() {
		$scope.data = [];
		var count = 0;
		angular.forEach($scope.years, function() {
			var inputdata = {};
			angular.forEach($scope.inputs, function($value, $key) {
				inputdata[$key] = $value[count].data;
			});
			inputdata.year = $scope.inputs.ar[count].year;
			$scope.data.push(inputdata);
			count++;
		});
		dataService.saveData($scope.data, company_id).then(function(response) {
			processResponse(response);
		});
	};
	//Clear the data from the input fields
	$scope.clear = function() {
		angular.forEach($scope.inputs, function(value1) {
			angular.forEach(value1, function(value2) {
				value2.data = "0";
			});
		});
	};
	//save without redirect
	//get the calculated data from server
	$scope.save = function() {
		saveCompanyData();
	}
	//save and redirect
	$scope.next = function() {
		saveCompanyData();
		$location.path('/portal');
	}
	//get calculated data from server and fill it in inputs 
	var getCalcData = function() {
			dataService.getData(company_id).then(function(dataResponse) {
				processResponse(dataResponse);
			});
		}
	var getCompanyData = function() {
			dataService.getCompData(company_id).then(function(response) {
				$scope.company = response.data;
				if($scope.company.sector === ""){
					$scope.company.sector = 'Select a sector';
				}
			});
		}
	var getSectors = function() {
			dataService.getSectors().then(function(response) {
				$scope.sectors = response.data;
			});
		}
	var saveCompanyData = function() {
		$scope.company.companyId = company_id;
			dataService.saveCompData($scope.company, userId).then(function(result){
				company_id = result.data;
				$rootScope.globals.currentUser.companyid = company_id;
				$cookieStore.put('globals', $rootScope.globals);
				$scope.processForm();
			});
		}
	var processResponse = function(dataResponse) {
			$scope.data = dataResponse.data;
			var count = 0;
			angular.forEach($scope.data, function(value) {
				var year = value.year;
				if ($scope.years.indexOf(year) < 0 && year !== 0) {
					nextyear = year;
					$scope.add();
					if (count == 0) {
						count++;
					}
				}
				angular.forEach(value, function(data, key) {
					if (!angular.equals(key, "year")) {
						$scope.inputs[key][count].data = data;
						$scope.inputs[key][count].year = year;
					}
				});
				count++;
			});
		}
	getSectors();
	getCompanyData();
	getCalcData();
});