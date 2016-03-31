var app = angular.module("TurnoverRatio");
app.service('TurnoverService', function($http) {
    this.getData = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/turnover/' + $id,
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
    this.getSource = function(id) {
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/source/' + id + "/source_id"
        })
    };
});

app.service('helpServiceTurnover', function(ngDialog) {
    this.showHelp = function(controller) {
        if (angular.equals(controller, 'dio')) {
            ngDialog.open({
                template: 'diotemplate', className: 'ngdialog-theme-default'
            });
        }
        else if (angular.equals(controller, 'dso')) {
            ngDialog.open({
                template: 'dsotemplate', className: 'ngdialog-theme-default'
            });
        }
        else if (angular.equals(controller, 'dpo')) {
            ngDialog.open({
                template: 'dpotemplate', className: 'ngdialog-theme-default'
            });
        }
    };
});

app.controller('headerCtrl', ['$scope', 'TurnoverService', '$rootScope', function($scope, TurnoverService, $rootScope) {
        $scope.company = {};
        TurnoverService.getCompany($rootScope.globals.currentUser.companyid).then(function(result) {
            $scope.company.name = result.data.company;
            $scope.company.sector = result.data.sector;
        });
        $scope.source_name = "";
        TurnoverService.getSource($scope.company.sector.id).then(function(result) {
            $scope.source_name = result.data;
        });
    }]);

app.controller('DIOCtrl', ['$scope', 'TurnoverService', 'helpServiceTurnover', '$rootScope', function($scope, TurnoverService, helpServiceTurnover, $rootScope) {
        $scope.help = function() {
            console.log('help called');
            helpServiceTurnover.showHelp('dio');
        };
        $scope.options = {
            scaleShowVerticalLines: false
        };
        $scope.data = [[]];
        $scope.labels = [];
        var sectorAvgDIO;
        //Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
        //Edited chart.js 2138, 2141, 2142.
        var colourArray = ["#6AFEB7"];
        TurnoverService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
            angular.forEach(result.data, function($value) {
                if (parseInt($value.year) > 0) {
                    $scope.data[0].push($value.dio);
                    $scope.labels.push($value.year);
                    colourArray.push("#AB7B0C");
                }
                else {
                    $scope.data[0].unshift(round($value.dio));
                    sectorAvgDIO = round($value.dio);
                    $scope.labels.unshift("Sector Average");
                }
            });
            //Add colours array to correct syntax for charts
            $scope.colours = [{fillColor: colourArray}];

        });
        $scope.condition = function(item) {
            if (item >= (sectorAvgDIO * 1.15)) {
                return "IMG/red.png";
            }
            else if (item >= (sectorAvgDIO * 1.05)) {
                return "IMG/orange.png";
            }
            else {
                return "IMG/green.png";
            }
        }
    }]);
app.controller('DSOCtrl', ['$scope', 'TurnoverService', 'helpServiceTurnover', '$rootScope', function($scope, TurnoverService, helpServiceTurnover, $rootScope) {
        $scope.help = function() {
            console.log('help called');
            helpServiceTurnover.showHelp('dso');
        };
        $scope.options = {
            scaleShowVerticalLines: false
        };
        $scope.data = [[]];
        $scope.labels = [];
        var sectorAvgDSO;
        //Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
        //Edited chart.js 2138, 2141, 2142.
        var colourArray = ["#6AFEB7"];
        TurnoverService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
            angular.forEach(result.data, function($value) {
                if (parseInt($value.year) > 0) {
                    $scope.data[0].push($value.dso);
                    $scope.labels.push($value.year);
                    colourArray.push("#AB7B0C");
                }
                else {
                    $scope.data[0].unshift(round($value.dso));
                    sectorAvgDSO = round($value.dso);
                    $scope.labels.unshift("Sector Average");
                }
            });
            //Add colours array to correct syntax for charts
            $scope.colours = [{fillColor: colourArray}];
        });
        $scope.condition = function(item) {
            if (item >= (sectorAvgDSO * 1.15)) {
                return "IMG/red.png";
            }
            else if (item >= (sectorAvgDSO * 1.05)) {
                return "IMG/orange.png";
            }
            else {
                return "IMG/green.png";
            }
        }

    }]);
app.controller('DPOCtrl', ['$scope', 'TurnoverService', 'helpServiceTurnover', '$rootScope', function($scope, TurnoverService, helpServiceTurnover, $rootScope) {
        $scope.help = function() {
            console.log('help called');
            helpServiceTurnover.showHelp('dpo');
        };
        $scope.options = {
            scaleShowVerticalLines: false
        };
        $scope.data = [[]];
        $scope.labels = [];
        var sectorAvgDPO;
        //Array which contains the colours from each bar. If no colour is found bar will be grayish. Not standard in Chart.js!
        //Edited chart.js 2138, 2141, 2142.
        var colourArray = ["#6AFEB7"];
        TurnoverService.getData($rootScope.globals.currentUser.companyid).then(function(result) {
            angular.forEach(result.data, function($value) {
                if (parseInt($value.year) > 0) {
                    $scope.data[0].push($value.dpo);
                    $scope.labels.push($value.year);
                    colourArray.push("#AB7B0C");
                }
                else {
                    $scope.data[0].unshift(round($value.dpo));
                    sectorAvgDPO = round($value.dpo);
                    $scope.labels.unshift("Sector Average");
                }
            });
            //Add colours array to correct syntax for charts
            $scope.colours = [{fillColor: colourArray}];
        });
        $scope.condition = function(item) {
            if (item >= (sectorAvgDPO * 0.95)) {
                return "IMG/green.png";
            }
            else if (item >= (sectorAvgDPO * 0.85)) {
                return "IMG/orange.png";
            }
            else {
                return "IMG/red.png";
            }
        }
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