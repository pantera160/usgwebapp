var app = angular.module("USGFinanceWebapp");
app.service('dataService', function($http) {
    this.getData = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/input/' + $id,
            cache:false
        });
    }
    this.saveData = function($data, $id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'POST',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/input/' + $id,
            data: $data
        });
    }
    this.getCompData = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/company/' + $id,
            cache:false
        });
    }
    this.saveCompData = function($data, $id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'POST',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/company/' + $id,
            data: $data
        });
    }
    this.getSectors = function() {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/sectors'
        });
    }
    this.uploadFile = function($file) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'POST',
            url: "http://wcstool-usg.rhcloud.com/rest/data/upload",
            data: $file,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
    }
});
app.directive('number', function() {
    return {
        // limit usage to argument only
        restrict: 'A',
        // require NgModelController, i.e. require a controller of ngModel directive
        require: 'ngModel',
        // create linking function and pass in our NgModelController as a 4th argument
        link: function(scope, element, attr, ctrl) {
            // please note you can name your function & argument anything you like

            function customValidator(ngModelValue) {
                // check if contains number
                // if it does contain number, set our custom `numberValidator`  to valid/true
                // otherwise set it to non-valid/false
                var regex = /^\d*.\d*$/;
                if (regex.test(ngModelValue)) {
                    ctrl.$setValidity('numberValidator', true);
                } else {
                    ctrl.$setValidity('numberValidator', false);
                }
                // we need to return our ngModelValue, to be displayed to the user(value of the input)
                return ngModelValue;
            }
            // we need to add our customValidator function to an array of other(build-in or custom) functions
            // I have not notice any performance issues, but it would be worth investigating how much
            // effect does this have on the performance of the app
            ctrl.$parsers.push(customValidator);
        }
    };
});
app.controller("HelpCtrl", function($scope, ngDialog) {
    $scope.help = function() {
        ngDialog.open({
            template: 'helptemplate',
            className: 'ngdialog-theme-default'
        });
    }
});
app.controller("MyForm", function($scope, dataService, $location, $rootScope, $cookieStore, ngDialog) {
    var startyear = new Date().getFullYear();
    var nextyear = new Date().getFullYear() - 1;
    $scope.data = null;
    $scope.sectors = [];
    $scope.company = {
        sector: "0"
    };
    $scope.error = false;
    var company_id = $rootScope.globals.currentUser.companyid;
    var userId = $rootScope.globals.currentUser.userid;
    //list of input fields
    //each data element will be linked to its input/label field to save data automatically
    var initialiseInput = function(year) {
        $scope.inputs = {
            "numberOfMonths": [{
                    "data": "0",
                    "year": year
                }],
            "fixedAssets": [{
                    "data": "0",
                    "year": year
                }],
            "intangiblesAssets": [{
                    "data": "0",
                    "year": year
                }],
            "propertyAssets": [{
                    "data": "0",
                    "year": year
                }],
            "finFixedAssets": [{
                    "data": "0",
                    "year": year
                }],
            "otherFixedAssets": [{
                    "data": "0",
                    "year": year
                }],
            "inventory": [{
                    "data": "0",
                    "year": year
                }],
            "ar": [{
                    "data": "0",
                    "year": year
                }],
            "cash": [{
                    "data": "0",
                    "year": year
                }],
            "currAssets": [{
                    "data": "0",
                    "year": year
                }],
            "totAssets": [{
                    "data": "0",
                    "year": year
                }],
            "equity": [{
                    "data": "0",
                    "year": year
                }],
            "ltFinDebt": [{
                    "data": "0",
                    "year": year
                }],
            "subordinatedDebt": [{
                    "data": 0,
                    "year": year
                }],
            "stFinDebt": [{
                    "data": "0",
                    "year": year
                }],
            "longTermLoans": [{
                    "data": "0",
                    "year": year
                }],
            "finDebt": [{
                    "data": "0",
                    "year": year
                }],
            "ap": [{
                    "data": "0",
                    "year": year
                }],
            "currLiabilities": [{
                    "data": "0",
                    "year": year
                }],
            "workingCapital": [{
                    "data": "0",
                    "year": year
                }],
            "netDebt": [{
                    "data": "0",
                    "year": year
                }],
            "turnover": [{
                    "data": "0",
                    "year": year
                }],
            "costOfSales": [{
                    "data": "0",
                    "year": year
                }],
            "comMatCon": [{
                    "data": "0",
                    "year": year
                }],
            "miscGoods": [{
                    "data": "0",
                    "year": year
                }],
            "depreciation": [{
                    "data": "0",
                    "year": year
                }],
            "ebit": [{
                    "data": "0",
                    "year": year
                }],
            "finRev": [{
                    "data": "0",
                    "year": year
                }],
            "finExp": [{
                    "data": "0",
                    "year": year
                }],
            "finExpInterest": [{
                    "data": "0",
                    "year": year
                }],
            "finExpBank": [{
                    "data": "0",
                    "year": year
                }],
            "finExpOther": [{
                    "data": "0",
                    "year": year
                }],
            "nrIncome": [{
                    "data": "0",
                    "year": year
                }],
            "nrCharges": [{
                    "data": "0",
                    "year": year
                }],
            "taxes": [{
                    "data": "0",
                    "year": year
                }],
            "recIncome": [{
                    "data": "0",
                    "year": year
                }],
            "netIncome": [{
                    "data": "0",
                    "year": year
                }],
            "ebitda": [{
                    "data": "0",
                    "year": year
                }]
        };
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
    //Errors map
    var initErrors = function() {
        $scope.errors = {
            fixedAssets: {},
            intangiblesAssets: {},
            propertyAssets: {},
            finFixedAssets: {},
            otherFixedAssets: {},
            cash: {},
            stFinDebt: {},
            costOfSales: {},
            comMatCon: {},
            miscGoods: {},
            taxes: {}
        };
    };
    //list of years
    //used to generate the headers
    $scope.years = [
        startyear];
    $scope.clear = false;
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
    $scope.addStartYear = function() {
        var newstartyear = $scope.years[0] + 1;
        //for each row add an extra column
        angular.forEach($scope.inputs, function($value) {
            $value.unshift({
                "data": "0",
                "year": newstartyear
            });
        });
        $scope.years.unshift(newstartyear);
    }
    //Save the input data to server
    $scope.processForm = function() {
        initErrors();
        if (!checkForError()) {
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
            $scope.error = false;
            addAlert('succes', "Data has been succesfully saved.")
            return true;
        }
        else {
            $scope.error = true;
            addAlert("danger", "There still remain some errors in the input data, please check the marked fields adn try again.");
            return false;
        }
    };
    //Clear the data from the input fields
    $scope.clear = function() {
        angular.forEach($scope.inputs, function(value1) {
            angular.forEach(value1, function(value2) {
                value2.data = "0";
            });
        });
        return false;
    };
    //save without redirect
    //get the calculated data from server
    $scope.save = function() {
        saveCompanyData();
    }
    //save and redirect
    $scope.next = function() {
        saveCompanyData();
        if (!checkForError()) {
            $location.path('/portal');
        }
    }
    //remove a year
    $scope.removeCol = function(year) {
        var yearindex = $scope.years.indexOf(year);
        angular.forEach($scope.inputs, function(result) {
            result.splice(yearindex, 1);
        });
        $scope.years.splice(yearindex, 1);
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
        });
    }
    var getSectors = function() {
        dataService.getSectors().then(function(response) {
            $scope.sectors = response.data;
        });
    }
    var saveCompanyData = function() {
        $scope.company.companyId = company_id;
        dataService.saveCompData($scope.company, userId).then(function(result) {
            company_id = result.data;
            $rootScope.globals.currentUser.companyid = company_id;
            $cookieStore.put('globals', $rootScope.globals);
            $scope.processForm();
        });
    }
    var processResponse = function(dataResponse) {
        $scope.data = dataResponse.data;
        var count = 0;
        var newestYear = 0;
        angular.forEach($scope.data, function(value) {
            if(value.year > newestYear){
                newestYear = value.year;
            }
            
            if (value.year > startyear) {
                startyear = value.year;
                $scope.years = [startyear];
                initialiseInput(value.year);
            }
            var year = value.year;
            if ($scope.years.indexOf(year) < 0 && year !== 0) {
                nextyear = year;
                $scope.add();
                if (count === 0) {
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
        if (newestYear !== 0) {
            if (newestYear < $scope.years[0]) {
                $scope.removeCol($scope.years[0]);
            }
        }
        
        new Date().getFullYear();
    }
    //Check weither the input data is valid according to the different sums
    var checkForError = function() {
        var inputs = $scope.inputs
        var response = false;
        for (i = 0; i < $scope.years.length; i++) {
            if (Math.round((parseFloat(inputs.intangiblesAssets[i].data) + parseFloat(inputs.propertyAssets[i].data) + parseFloat(inputs.finFixedAssets[i].data) + parseFloat(inputs.otherFixedAssets[i].data)) * 10) / 10 != parseFloat(inputs.fixedAssets[i].data)) {
                console.log("assets not equal");
                $scope.errors.fixedAssets[inputs.fixedAssets[i].year] = 'error';
                $scope.errors.intangiblesAssets[inputs.fixedAssets[i].year] = 'toError';
                $scope.errors.propertyAssets[inputs.fixedAssets[i].year] = 'toError';
                $scope.errors.finFixedAssets[inputs.fixedAssets[i].year] = 'toError';
                $scope.errors.otherFixedAssets[inputs.fixedAssets[i].year] = 'toError';
                response = true;
            }
            if (Math.round((parseFloat(inputs.comMatCon[i].data) + parseFloat(inputs.miscGoods[i].data)) * 10) / 10 != parseFloat(inputs.costOfSales[i].data)) {
                console.log("cost of sales not equal");
                $scope.errors.costOfSales[inputs.costOfSales[i].year] = 'error';
                $scope.errors.comMatCon[inputs.costOfSales[i].year] = 'toError';
                $scope.errors.miscGoods[inputs.costOfSales[i].year] = 'toError';
                response = true;
            }
        }
        return response;
    };
    this.setInputData = function(inputdata) {
        $scope.inputs = inputdata;
    }
    $scope.uploadxbrl_popup = function() {
        ngDialog.open({
            template: 'uploadtemplate',
            className: 'ngdialog-theme-default',
            scope: $scope
        });
    };
    $scope.upload = function() {
        console.log("upload clicked");
        console.log("uploadfile: " + $scope.files[0]);
        var fd = new FormData();
        fd.append('file', $scope.files[0], 'xbrlfile.xbrl');
        dataService.uploadFile(fd).then(function(response) {
            initialiseInput(startyear);
            if ($scope.years.lenght > 0) {
                $scope.removeCol($scope.years[0]);
            }
            for (i = 0; i < response.data.length; i++) {
                var temp = response.data[i]
                $scope.years.push(temp.year);
                angular.forEach(temp, function(value, key) {
                    if (key !== 'year')
                        $scope.inputs[key].push({
                            "data": value,
                            "year": temp.year
                        })
                })
            }
            nextyear = $scope.years[$scope.years.length - 1] - 1;
            ngDialog.closeAll();
        });
    };
    $scope.setFiles = function(element) {
        $scope.$apply(function($scope) {
            console.log('files:', element.files);
            // Turn the FileList object into an Array
            $scope.files = []
            for (var i = 0; i < element.files.length; i++) {
                $scope.files.push(element.files[i])
            }
        });
    };
    
    $scope.alerts = {};
    
    $scope.closeAlert = function(index){
      $scope.alerts.splice(index, 1);  
    };
    
    addAlert = function(errortype, errormsg){
        $scope.alerts.push({type: errortype, msg: errormsg});
    }
    
    initialiseInput(startyear);
    initErrors();
    getSectors();
    getCompanyData();
    getCalcData();
});
