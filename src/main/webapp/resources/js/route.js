var app = angular.module('app', ['ngRoute','ngSanitize']);
	app.config(['$routeProvider', ($routeProvider) => {
		$routeProvider.when("/apply", {templateUrl : 'apply', controller: "app_apply"})
					  .when("/list", {templateUrl : 'list', controller: "app_list"})
					  .when("/edit", {templateUrl : 'edit', controller: "app_edit"})
					  .when("/notice", {templateUrl : 'notice', controller: "app_notice"})
					  .when("/open_notice", {templateUrl : 'open_notice', controller: "app_open_notice"})
					  .otherwise({redirectTo: "/"});
					  
	}]);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_apply', function($scope, $routeParams, $http, $rootScope) {
			$http({
				  method: 'POST',
				  url: '/apply',
				  params: {}
			}).then(function(response) {
				$scope.apply = response.data.result;
			});
	});
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_list', function($scope, $routeParams, $http, $rootScope) {
		$http({
			  method: 'POST',
			  url: '/list',
			  params: {}
		}).then(function(response) {
			$scope.apply = response.data.result;
		});
	});
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_edit', function($scope, $routeParams, $http, $rootScope) {
		CKEDITOR.replace('editor', {});
		$scope.insert = function() {
			var data = CKEDITOR.instances.editor.getData();	
			$http({
				  method: 'POST',
				  url: '/edit_insert',
				  params: {'Title' : $scope.edit_title,
					  	   'editor' : data
				  }
			}).then(function(response) {
				window.location.href = "/Main#!/notice"
			});
		}
	});
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_notice', function($scope, $routeParams, $http, $rootScope,$sce) {
		$scope.currentPage = 1;
		$scope.boolean = false;
		CKEDITOR.replace('alt', {});
		$http({
			  method: 'POST',
			  url: '/notice',
			  params: {}
		}).then(function(res) {
			$scope.notice = res.data.data;
			$scope.notice_size = res.data.data.length;
			$scope.page_prev.data = false;
			$scope.page_next.data = false;				
			$scope.pageSize = Math.ceil($scope.notice_size/5);
			if($scope.currentPage == 1) {
				$scope.page_prev.data = true;
			}else{
				$scope.page_prev.data = false;
			}
			if($scope.currentPage + 5 >= $scope.pageSize){
				 $scope.page_next.data = true;
			}else{
				$scope.page_next.data = false;
			}
		});
		$scope.test = function(index){
			var len = index-1;
			$http({
				  method: 'POST',
				  url: '/notice',
				  params: {}
			}).then(function(res) {
				var page_len = (res.data.data.length) - len;
				var page_list = [];
				for(var i = 0; i < page_len; i++){
					page_list[i] = res.data.data[i];
				}
				$scope.notice = page_list;
			});
		}
		$scope.page_prev = function(){
			$scope.currentPage = $scope.currentPage-5;
			if($scope.currentPage <= $scope.pageSize) $scope.page_next.data = false;
		}
		$scope.page_next = function(){
			$scope.currentPage = $scope.currentPage+5;
			if($scope.currentPage+5 >= $scope.pageSize) $scope.page_next.data = true;
		}
		$scope.view = function(index) {
			var len = ($scope.notice.length-1)-index;
			var data = $scope.notice[len];
			var params = {'No' : $scope.notice[len].No,
				     'Nickname' :  $scope.notice[len].Nickname,
				     'Title' :  $scope.notice[len].Title
			};
			$http({
				method:'POST',
				url: '/view',
				params : params
			}).then(function(res) { 
				$scope.views = res.data.result[0];
				$scope.views.Tags = $sce.trustAsHtml(res.data.result[0].Tags);
			})
		}
		$scope.alt = function(){
			CKEDITOR.instances.alt.setData($scope.views.Tags);
			$scope.boolean = !$scope.boolean;
		}
		$scope.alt_data = function(){
			var data = CKEDITOR.instances.alt.getData();	
			$http({
				method:'POST',
				url: '/edit_alt',
				params: {'Title' : $scope.views.Title,
						 'editor': data,
						 'No'	 : $scope.views.No
						 
			  }
			}).then(function() {
				window.location.href = "/Main#!/notice"
				$scope.boolean = !$scope.boolean;
				$http({
					  method: 'POST',
					  url: '/notice',
					  params: {}
				}).then(function(res) {
					$scope.notice = res.data.data;
				});
			})
		}
		$scope.cancel = function(){
			$scope.boolean = !$scope.boolean;
		}
		$scope.delete_data = function(){
			$http({
				method:'POST',
				url: '/edit_delete',
				params: {'Title' : $scope.views.Title,
						 'No' : $scope.views.No
			  }
			}).then(function() {
				window.location.href = "/Main#!/notice"
				$http({
					  method: 'POST',
					  url: '/notice',
					  params: {}
				}).then(function(res) {
					$scope.notice = res.data.data;
				});
			})
		}
	});
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_root', function($scope, $routeParams, $http, $rootScope) {
		$http({
			  method: 'POST',
			  url: '/Root',
			  params: {}
		}).then(function(response) {
			$scope.show = response.data.result;
			$scope.userimg = "/../upload/" + response.data.data[0].img;
			$scope.usernick = response.data.data[0].Nickname;
		});
	});
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_open_notice', function($scope, $routeParams, $http, $rootScope) {
		$http({
			  method: 'POST',
			  url: '/open_notice',
			  params: {}
		}).then(function(res) {
			$scope.notice = res.data.data;
		});
		$scope.view = function(index) {
			var len = ($scope.notice.length-1)-index;
			var data = $scope.notice[len];
			var params = {'No' : $scope.notice[len].No,
				     'Nickname' :  $scope.notice[len].Nickname,
				     'Title' :  $scope.notice[len].Title
			};
			
			$http({
				method:'POST',
				url: '/view',
				params : params
			}).then(function(res) {
				$scope.views = res.data.result[0];
			})
		}
	});