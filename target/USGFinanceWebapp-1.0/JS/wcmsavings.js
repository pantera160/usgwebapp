var app = angular.module("WCMSavings");

app.service('WCMService', function($http) {
    this.getData = function($id, $year) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/wcm/' + $id + '/' + $year
        });
    };
    this.saveData = function($data, $id, $year) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'POST',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/wcm/' + $id + '/' + $year,
            data: $data
        });

    };
    this.getCompany = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/company/' + $id,
            cache: true
        });
    };
    this.getTurnover = function($id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/turnover/' + $id,
            cache: true
        });
    };
    this.getYears = function($id) {
        //$http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/data/years/' + $id,
            cache: true
        })
    }
});


app.controller('headerCtrl', ['$scope', 'WCMService', '$rootScope', function($scope, WCMService, $rootScope) {
        $scope.company = {};
        WCMService.getCompany($rootScope.globals.currentUser.companyid).then(function(result) {
            $scope.company.name = result.data.company;
            $scope.company.sector = result.data.sector;
        })
    }]);

app.controller('WCMSCtrl', ['$scope', 'WCMService', '$location', '$rootScope', function($scope, WCMService, $location, $rootScope) {
        $scope.data = {};
        $scope.sector = {};
        $scope.back = function() {
            $location.path('/portal');
        };
        
        $scope.year = {};

        var getSectorAvg = function() {
            WCMService.getTurnover($rootScope.globals.currentUser.companyid).then(function(result) {
                angular.forEach(result.data, function(value) {
                    if (!(parseInt(value.year) > 0)) {
                        $scope.sector.dio = value.dio;
                        $scope.sector.dso = value.dso;
                        $scope.sector.dpo = value.dpo;
                    }
                })
            });
        }

        var getData = function() {
            WCMService.getYears($rootScope.globals.currentUser.companyid).then(function(response) {
                $scope.years = response.data;
                $scope.year = $scope.years[0];
                WCMService.getData($rootScope.globals.currentUser.companyid, $scope.year.name).then(function(response) {
                    $scope.data = response.data;
                });
            });

        };

        $scope.save = function() {
            WCMService.saveData($scope.data, $rootScope.globals.currentUser.companyid, $scope.year.name).then(function(result) {
                $scope.data = result.data;
            });
        };
        $scope.reload = function() {
            WCMService.getData($rootScope.globals.currentUser.companyid, $scope.year.name).then(function(response) {
                $scope.data = response.data;
            });
        };
        getData();
        getSectorAvg();
    }]);


