var app = angular.module("CoverageRatio")
app.service('CoverageService', function($http) {
    this.getData = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/coverage/' + $id,
            cache: false
        });
    };
    this.getCompany = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/company/' + $id
        });
    };
    this.getSource = function(id, type){
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/source/' + id + "/source_id2"
        })
    };
});
app.service('helpServiceCoverage', function(ngDialog) {
    this.showHelp = function(controller) {
        console.log('showHelp called');
        if (angular.equals(controller, 'FinCharges')) {
            ngDialog.open({
                template: 'finchargestemplate',
                className: 'ngdialog-theme-default'
            });
        } else if (angular.equals(controller, 'FinDebt')) {
            ngDialog.open({
                template: 'findebttemplate',
                className: 'ngdialog-theme-default'
            });
        }
    };
});

app.controller('headerCtrl', ['$scope', 'CoverageService', '$rootScope', function($scope, CoverageService, $rootScope) {
        $scope.company = {};
        CoverageService.getCompany($rootScope.globals.currentUser.companyid).then(function(result) {
            $scope.company.name = result.data.company;
            $scope.company.sector = result.data.sector;
        });
        $scope.source_name = "";
        CoverageService.getSource($scope.company.sector.id).then(function(result) {
            $scope.source_name = result.data;
        });
    }]);
app.controller('FinChargesCtrl', ['$scope', 'CoverageService', 'helpServiceCoverage', '$rootScope', function($scope, CoverageService, helpServiceCoverage, $rootScope) {
        $scope.help = function() {
            helpServiceCoverage.showHelp('FinCharges');
        };
        $scope.options = {
            scaleShowVerticalLines: false
        };
        $scope.data = [
            []
        ];
        $scope.labels = [];
        //Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
        //Edited chart.js 2138, 2141, 2142.
        var colourArray = ["#6AFEB7"];
        CoverageService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
            angular.forEach(result.data, function($value) {
                if (parseInt($value.year) > 0) {
                    $scope.data[0].push(round($value.ebitfinExp));
                    $scope.labels.push($value.year);
                    colourArray.push("#AB7B0C");
                } else {
                    $scope.data[0].unshift(round($value.ebitfinExp));
                    $scope.labels.unshift("Sector Average");
                }
            });
            //Add colours array to correct syntax for charts
            $scope.colours = [{
                    fillColor: colourArray
                }];
        });
        $scope.condition = function(item) {
            if (item >= 2.85) {
                return "IMG/green.png";
            }
            else if (item >= 2.55) {
                return "IMG/orange.png";
            }
            else {
                return "IMG/red.png";
            }
        };
    }]);
app.controller('FinDebtEBITDACtrl', ['$scope', 'CoverageService', 'helpServiceCoverage', '$rootScope', function($scope, CoverageService, helpServiceCoverage, $rootScope) {
        $scope.help = function() {
            helpServiceCoverage.showHelp('FinDebt');
        };
        $scope.options = {
            scaleShowVerticalLines: false
        };
        $scope.data = [
            []
        ];
        $scope.labels = [];
        //Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
        //Edited chart.js 2138, 2141, 2142.
        var colourArray = ["#6AFEB7"];
        CoverageService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
            angular.forEach(result.data, function($value) {
                if (parseInt($value.year) > 0) {
                    $scope.data[0].push(round($value.netDebtEBITDA));
                    $scope.labels.push($value.year);
                    colourArray.push("#AB7B0C");
                } else {
                    $scope.data[0].unshift(round($value.netDebtEBITDA));
                    $scope.labels.unshift("Sector Average");
                }
            });
            //Add colours array to correct syntax for charts
            $scope.colours = [{
                    fillColor: colourArray
                }];
        });
        $scope.condition = function(item) {
            if (item >= 2.375) {
                return "IMG/red.png";
            }
            else if (item >= 2.125) {
                return "IMG/orange.png";
            }
            else {
                return "IMG/green.png";
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