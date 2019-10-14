var app = angular.module('app', ['ngRoute','ngSanitize']);
	app.config(['$routeProvider', ($routeProvider) => {
		$routeProvider.when("/apply", {templateUrl : 'apply', controller: "app_apply"})
					  .when("/list", {templateUrl : 'list', controller: "app_list"})
					  .when("/edit", {templateUrl : 'edit', controller: "app_edit"})
					  .when("/notice", {templateUrl : 'notice', controller: "app_notice"})
					  .when("/open_notice", {templateUrl : 'open_notice', controller: "app_open_notice"})
					  .when("/open_table", {templateUrl : 'open_table', controller: "app_open_table"})
					  .otherwise({redirectTo: "/"});
					  
	}]);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	app.controller('app_apply', function($scope, $routeParams, $http, $rootScope) {
			$scope.reload = function(){
				$http({
					  method: 'POST',
					  url: '/apply',
					  params: {}
				}).then(function(response) {
					$scope.apply = response.data.result;
				});
			}
			$scope.user_apply = function(index){
				
				var test = $scope.apply[index];
				console.log(test);
				$http({
					  method: 'POST',
					  url: '/user_apply',
					  params: {
						  'User' : $scope.apply[index].USER,
						  'Nickname' : $scope.apply[index].Nickname
					  }
				}).then(function(response) {
					alert(response.data.msg);
				});
			}
			$scope.reload();
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
		$scope.page = function(index){
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
				if(res.data.result[0].Open == "N"){
					$scope.views.Opendata = "공개전환";
				}else{
					$scope.views.Opendata = "비공개전환";
				}
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
		$scope.notice_open = function(){
			console.log($scope.views);
			var params = {'No' : $scope.views.No,
				     	  'Title' : $scope.views.Title,
				     	  'Open' : $scope.views.Open
			};
			$http({
				  method: 'POST',
				  url: '/notice_open',
				  params: params
			}).then(function(res) {
				console.log(res);
			});
		};
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
		$scope.currentPage = 1;
		$http({
			  method: 'POST',
			  url: '/open_notice',
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
		$scope.open_page = function(index){
			var len = index-1;
			$http({
				  method: 'POST',
				  url: '/open_notice',
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
	});
	app.controller('app_open_table', function($scope, $routeParams, $http, $rootScope) {
		var quarter = "";
		var No = 0;
		$scope.currentPage = 1;
		$scope.boolean = false;
		CKEDITOR.replace('table_editor', {});
		$scope.table_reload = function(){
			$http({
				  method: 'POST',
				  url: '/open_table',
				  params: {}
			}).then(function(res) {
				$scope.opentable = res.data.result;
				$scope.table_size = res.data.result.length;
				$scope.table_page_prev = false;
				$scope.table_page_next = false;				
				$scope.table_pageSize = Math.ceil($scope.table_size/10);
				if($scope.currentPage == 1) {
					$scope.table_page_prev = true;
				}else{
					$scope.table_page_prev = false;
				}
				if($scope.currentPage + 5 >= $scope.pageSize){
					 $scope.table_page_next = true;
				}else{
					$scope.table_page_next = false;
				}
				$scope.table_page_prev.data = function(){
					$scope.currentPage = $scope.currentPage-5;
					if($scope.currentPage <= $scope.pageSize) $scope.table_page_next = false;
				}
				$scope.table_page_next.data = function(){
					$scope.currentPage = $scope.currentPage+5;
					if($scope.currentPage+5 >= $scope.pageSize) $scope.table_page_next = true;
				}
			});
		}
		$scope.table_page = function(index){
			var len = index-1;
			$http({
				  method: 'POST',
				  url: '/open_table',
				  params: {}
			}).then(function(res) {
				var page_len = (res.data.result.length) - len;
				var page_list = [];
				for(var i = 0; i < page_len; i++){
					page_list[i] = res.data.result[i];
				}
				$scope.opentable = page_list;
			});
		}
		$scope.table_write = function(){
			$scope.boolean = true;
			quarter = "insert";
		}
		$scope.table_cancel = function(){
			$scope.boolean = false;
			quarter = "";
			$scope.table_views.Title = "";
			CKEDITOR.instances.table_editor.setData("");
		}
		$scope.table_insert = function() {
			var data = CKEDITOR.instances.table_editor.getData();	
			$http({
				  method: 'POST',
				  url: '/table_insert',
				  params: {'Title' : $scope.table_views.Title,
					  	   'editor' : data,
					  	   'quarter' : quarter,
					  	   'No' : No
				  }
			}).then(function(response) {
				window.location.href = "/Main#!/open_table"
				$scope.boolean = false;
				$scope.table_reload();
			});
		}
		$scope.table_view = function(index){
				var len = ($scope.opentable.length-1)-index;
				var params = {'No' : $scope.opentable[len].No,
					     'Nickname' :  $scope.opentable[len].Nickname,
					     'Title' :  $scope.opentable[len].Title,
					     'col' :  $scope.opentable[len].col + 1,
				};
				title = $scope.opentable[len].Title;
				$http({
					method:'POST',
					url: '/table_view',
					params : params
				}).then(function(res) {
					$scope.table_views = res.data.result[0];
					$scope.table_reload();
				})
		};
		$scope.table_alt = function(){
			CKEDITOR.instances.table_editor.setData($scope.table_views.Tags);
			$scope.boolean = !$scope.boolean;
			quarter = "alt";
			No = $scope.table_views.No;
		}
		$scope.table_delete = function(){
			No = $scope.table_views.No;
			$http({
				  method: 'POST',
				  url: '/table_delete',
				  params: {'Title' : $scope.table_views.Title,
					  	   'No' : No
				  }
			}).then(function(response) {
				window.location.href = "/Main#!/open_table"
				$scope.table_reload();
			});
		}
		$scope.table_search = function() {
			$http({
				  method: 'POST',
				  url: '/table_search',
				  params: {'search' : $scope.search}
			}).then(function(res) {
				$scope.opentable = res.data.result;
			});
		}
		$scope.table_reload();
	});