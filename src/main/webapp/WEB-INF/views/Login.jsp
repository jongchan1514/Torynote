<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Tory Note</title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script> 
		<link rel="stylesheet" href="/resources/css/login.css?after">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>	
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/css/bootstrap-material-datetimepicker.min.css" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/js/bootstrap-material-datetimepicker.min.js"></script>


		<script>
		jQuery(document).ready(function($){
		    $('.datepicker').bootstrapMaterialDatePicker({
		    	weekStart : 0,
		    	time: false,
		    	});
		})
	    
		var ngApp = angular.module('App', []);
		ngApp.controller('Controller', function($rootScope, $scope, $http) {
			$scope.Login = function() {
				$http({
					  method: 'POST',
					  url: '/Login',
					  params: {'User' : $scope.User_login,
							   'Password' : $scope.Password_login,
							   'Nickname' : "test",
							   'Sex' : "a" ,
							   'Age' : "a"
					  }
				}).then(function(response) {
					var msg = response.data.Check
					 alert(response.data.msg);
					 if(msg == "Ok"){
						 window.location.href = "/Main";
						 }
				});
			}
			$scope.sign = function() {
				var date = $("#date").bootstrapMaterialDatePicker("getDate")[0].value;
				$http({
					  method: 'POST',
					  url: '/Sign',
					  params: {'User' : $scope.User_Sign,
							   'Password' : $scope.Password_Sign,  
							   'Nickname' : $scope.Nickname_Sign,
							   'Sex' : $scope.Sex_Sign,
							   'Age' : date 
					  }
				}).then(function(response) {
					alert(response.data.msg);
					 if(response.data.Check == "Success"){
						window.location.href = "/"
					} 
				});
			}
			$scope.rotate = function(check){
				$scope.rotate.data = check;
			}
		});
		</script>
	</head>
	<body data-ng-App="App" data-ng-controller="Controller">
		<section style="height: 100vh;">
    		<div style="background-attachment: fixed; background-size: cover; width: 100%; height: 100vh; position: relative;"  >
    			<div class="baslik">
	        		<b style="font-size: 101px; text-align: center; margin-bottom: -21px; display: block;">Tory Note</b>
		    	</div>
		    	<section>
		    		<form data-ng-show = "!rotate.data">
		        		<div class="arkalogin">
		            		<div class="loginbaslik">Login</div>
		            		<hr style="border: 1px solid #ccc;">
		            		<input class="giris" type="text" data-ng-model="User_login" placeholder="누구인가요?" required autofocus>
		            		<input class="giris" type="password" data-ng-model="Password_login" placeholder="비밀번호를 입력해주세요" required autofocus>
		            		<button class="butonlogin" data-ng-click=Login() type="submit">로그인</button>
		            		<button id="buton2" data-ng-click=rotate(true) type="button">계정이 없으신가요?</button>
		        		</div>
		    		</form>
		    		<form data-ng-show = "rotate.data">
		        		<div class="arkaSign">
		            		<div class="loginbaslik">회원가입</div>
		            		<hr style="border: 1px solid #ccc;">
		            		<input id="use" class="giris" type="text" data-ng-model="User_Sign" placeholder="계정 4~16자리 사이로 입력하세요" required autofocus>
		            		<input id="pwd" class="giris" type="password" data-ng-model="Password_Sign" placeholder="비밀번호 8~16자리 사이로 입력하세요" required autofocus>
		            		<input id="nik" class="giris" type="text" data-ng-model="Nickname_Sign" placeholder="닉네임 4~16자리 사이로 입력하세요" required autofocus>
		            		<select name="Sex" data-ng-model="Sex_Sign" class="giris" required autofocus>
		            			<option value="">당신의 성별은?</option>
		            			<option value="male">남성</option>
		            			<option value="female">여성</option>
		            		</select>
				            <input id="date" class="form-control datepicker giris" placeholder="생년월일을 입력 해 주세요"  required autofocus>
		            		<button id="buton3" data-ng-click=sign() type="button">이 정보로 가입하겠습니다.</button>
		            		<button class="butonlogin" data-ng-click=rotate(false) type="button">가입안할래요</button>
		        		</div>
		    		</form>
		    	</section><br>
		    </div>
    	</section>
	</body>
</html>
