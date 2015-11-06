/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var app = angular.module("USGFinanceWebapp", ['ngResource'])
 app.factory('InputService', function ($resource) {
    return $resource('localhost:8080/usgfinancewebapp/rest/input/:id',{id: "@id"});
});

 app.controller("MyForm", function ($scope) {	
    var startyear = new Date().getFullYear();
    var nextyear = new Date().getFullYear() - 1;
    
    $scope.add = function(){
    $scope.inputs.forEach(function($row){
      $row.cols.push({"year" : String(nextyear)});
    });
    nextyear--;
  };
    
    $scope.inputs = [{"title" : "Number of months", "NBBCode" : "", "source" : "", "sub" : "NumberMonths", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Fixed Assets", "NBBCode" : "20/28", "source" : "Financial Statements", "sub" : "FixedAssets", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Inventory", "NBBCode" : "3", "source" : "Financial Statements or Annexes", "sub" : "Inventory", "cols" : [{"year" : String(startyear), "data" : ""}]}, 	{"title" : "Accounts receivable", "NBBCode" : "40", "source" : "Financial Statements or Annexes", "sub" : "AR", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Cash", "NBBCode" : "50/53+54/58", "source" : "Financial Statements", "sub" : "Cash", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Current Assets", "NBBCode" : "29/58", "source" : "Financial Statements", "sub" : "CurrAssets", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Total Assets", "NBBCode" : "20/58", "source" : "Financial Statements", "sub" : "TotAssets", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Equity", "NBBCode" : "", "source" : "Financial Statements", "sub" : "Equity", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Long term financial debt", "NBBCode" : "170/4", "source" : "Financial Statements", "sub" : "LtFinDebt", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Short term financial debt", "NBBCode" : "42+43", "source" : "Financial Statements", "sub" : "StFinDebt", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Accounts Payable", "NBBCode" : "44", "source" : "Financial Statements or Annexes", "sub" : "AP", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Current Liabilities", "NBBCode" : "17/49", "source" : "Financial Statements", "sub" : "CurrLiabilities", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Working Capital", "NBBCode" : "", "source" : "Automatic computation", "sub" : "WorkingCapital", "cols" : [{"year" : String(startyear), "data" : ""}]}, 	
    {"title" : "Net Debt", "NBBCode" : "", "source" : "Automatic computation", "sub" : "NetDebt", "cols" : [{"year" : String(startyear), "data" : ""}]}, 
    {"title" : "Turnover", "NBBCode" : "70/74", "source" : "Financial Statements", "sub" : "Turnover", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Cost of sales", "NBBCode" : "60+61", "source" : "Financial Statements or Annexes", "sub" : "CostofSales", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Deprecation", "NBBCode" : "630", "source" : "Financial Statements or Annexes", "sub" : "Deprecation", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "EBIT (Earnings Before Interest, Taxes)", "NBBCode" : "9901", "source" : "Financial Statements or Annexes", "sub" : "EBIT", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Financial Revenu", "NBBCode" : "75", "source" : "Financial Statements", "sub" : "FinRev", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Financial Expenses", "NBBCode" : "65", "source" : "Financial Statements", "sub" : "FinExp", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "of which interest charges", "NBBCode" : "650", "source" : "Financial Statements or Annexes", "sub" : "InterestCharges", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "of which bank charges", "NBBCode" : "", "source" : "Financial Statements or Annexes", "sub" : "BankCharges", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "of which other financial charges", "NBBCode" : "", "source" : "Financial Statements or Annexes", "sub" : "FinCharges", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Non recurring income", "NBBCode" : "76", "source" : "Financial Statements or Annexes", "sub" : "NrIncome", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Non recurring charges", "NBBCode" : "66", "source" : "Financial Statements or Annexes", "sub" : "NumberMonths", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Taxes", "NBBCode" : "D", "source" : "Financial Statements", "sub" : "Taxes", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Recurring incoming", "NBBCode" : "9902", "source" : "Automatic computation", "sub" : "RecIncome", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "Net Income", "NBBCode" : "9904", "source" : "Automatic computation", "sub" : "NetIncome", "cols" : [{"year" : String(startyear), "data" : ""}]},
    {"title" : "EBITDA (Earnings Before Interest, Taxes, Deprecation and Amortization", "NBBCode" : "", "source" : "Automatic computation", "sub" : "EBITDA", "cols" : [{"year" : String(startyear), "data" : ""}]}];
    
    $scope.processForm = function(){
    	InputService.save({});//call for each input column.
    };
    });

    

    