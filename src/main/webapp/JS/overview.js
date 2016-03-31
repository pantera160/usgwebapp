var app = angular.module('Overview');

app.service('CompanyService', function($http) {
    this.getData = function(id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://wcstool-usg.rhcloud.com/rest/crm/companies/' + id,
            cache: false
        });
    };
    this.remove = function(id) {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'DELETE',
            url: 'http://wcstool-usg.rhcloud.com/rest/crm/company/' + id
        });
    }
});

app.controller('OverviewCtrl', ['$rootScope', '$scope', 'CompanyService', '$location', '$cookieStore', function($rootScope, $scope, CompanyService, $location, $cookieStore) {

        $scope.rows = {};
        $scope.deleting = {};

        $scope.confirm = function(id) {
            $scope.deleting[id] = true;
        }

        $scope.decline = function(id) {
            $scope.deleting[id] = false;
        }

        var getCompanies = function() {
            var userid = $rootScope.globals.currentUser.userid;
            CompanyService.getData(userid).then(function(result) {
                $scope.companies = result.data;
                angular.forEach($scope.companies, function(company) {
                    var cmpid = company.id;
                    $scope.deleting[cmpid] = false;
                    $scope.rows[cmpid] = "clickable";
                });
            });
        };

        $scope.goTo = function(companyId) {
            $rootScope.globals.currentUser.companyid = companyId;
            $cookieStore.put('globals', $rootScope.globals);
            $location.path('/inputs');
        }

        $scope.clickable = "clickable";

        $scope.hovertd = function(id) {
            if ($scope.rows[id] === "clickable") {
                $scope.rows[id] = "hover";
            } else {
                $scope.rows[id] = "clickable";
            }
        }

        $scope.getClass = function(id) {
            return $scope.rows.id;
        }

        $scope.new = function() {
            $location.path('/inputs');
        }

        var clearCompId = function() {
            $rootScope.globals.currentUser.companyid = -1;
            $cookieStore.put('globals', $rootScope.globals);
        }

        $scope.remove = function(companyId) {
            CompanyService.remove(companyId);
            getCompanies();
        }

        clearCompId();
        getCompanies();
    }]);