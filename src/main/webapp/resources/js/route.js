var app = angular.module('app', ['ngRoute','ngSanitize']);
	app.config(['$routeProvider', ($routeProvider) => {
		$routeProvider.when("/apply", {templateUrl : 'apply', controller: "app_apply"})
					  .when("/list", {templateUrl : 'list', controller: "app_list"})
					  .when("/edit", {templateUrl : 'edit', controller: "app_edit"})
					  .when("/notice", {templateUrl : 'notice', controller: "app_notice"})
					  .otherwise({redirectTo: "/"});
					  
	}]);
	app.controller('app_apply', function($scope, $routeParams, $http, $rootScope) {
			$http({
				  method: 'POST',
				  url: '/apply',
				  params: {}
			}).then(function(response) {
				$scope.apply = response.data.result;
			});
	});
	app.controller('app_list', function($scope, $routeParams, $http, $rootScope) {
		$http({
			  method: 'POST',
			  url: '/list',
			  params: {}
		}).then(function(response) {
			$scope.apply = response.data.result;
		});
	});
	app.controller('app_edit', function($scope, $routeParams, $http, $rootScope) {
		
		CKEDITOR.replace('editor', {
            uiColor: '#ffe2ed',
            resize_enabled: false,
            filebrowserUploadUrl : '/imageUpload',
//            extraPlugins : 'uploadimage',
            toolbar: 
            	[
                  { name: 'document', items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
                  { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
                  { name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
                  { name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
                  '/',
                  { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] },
                  { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
                  { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
                  { name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
                  '/',
                  { name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
                  { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
                  { name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
                  { name: 'about', items: [ 'About' ] }
            ]
         });
	});
	app.controller('app_notice', function($scope, $routeParams, $http, $rootScope) {
		$scope.boolean = false;
		CKEDITOR.replace('alt', {
			 uiColor: '#ffe2ed',
	            resize_enabled: false,
	            filebrowserUploadUrl : '/imageUpload',
	            toolbar: 
	            	[
	                  { name: 'document', items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
	                  { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
	                  { name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
	                  { name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
	                  '/',
	                  { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] },
	                  { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
	                  { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
	                  { name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
	                  '/',
	                  { name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
	                  { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
	                  { name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
	                  { name: 'about', items: [ 'About' ] }
	            ]
		});
		$http({
			  method: 'POST',
			  url: '/notice',
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
		$scope.alt = function(){
			CKEDITOR.instances.alt.setData($scope.views.Tags);
			$scope.boolean = !$scope.boolean;
		}
	});
	app.controller('app_root', function($scope, $routeParams, $http, $rootScope) {
		$http({
			  method: 'POST',
			  url: '/Root',
			  params: {}
		}).then(function(response) {
			$scope.show = response.data.result;
		});
	});