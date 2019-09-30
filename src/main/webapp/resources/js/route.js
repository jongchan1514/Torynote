var app = angular.module('app', ['ngRoute']);
	app.config(['$routeProvider', ($routeProvider) => {
		$routeProvider.when("/apply", {templateUrl : 'apply', controller: "app_apply"})
					  .when("/list", {templateUrl : 'list', controller: "app_list"})
					  .otherwise({redirectTo: "/"});
	}]);
	app.controller('app_apply', function($scope, $routeParams) {
		console.log("app_apply", $routeParams.param);
	});
	
	app.controller('app_list', function($scope, $routeParams, $http) {
		$scope.Login = function() {
			$http({
				  method: 'POST',
				  url: '/Login',
				  params: {}
			}).then(function(response) {
					console.log(test)
			});
		}
	});