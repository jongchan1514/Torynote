<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="Responsive sidebar template with sliding effect and dropdown menu based on bootstrap 3">
	    <title>Tory Note</title>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
	    <link rel="stylesheet" href="/resources/css/Home.css">
	    <script src="/resources/js/home.js"></script>
	    <script src="/resources/js/route.js"></script>
	    <script>

	    </script>
	</head>
	<body data-ng-app="app" id="torynote">
		<div class="page-wrapper chiller-theme toggled">
		  <a id="show-sidebar" class="btn btn-sm btn-dark" href="">
		    <i class="fas fa-bars"></i>
		  </a>
		  <nav id="sidebar" class="sidebar-wrapper">
		    <div class="sidebar-content">
		      <div class="sidebar-brand">
		        <a href="/Main">Tory Note</a>
		        <div id="close-sidebar">
		          <i class="fas fa-times"></i>
		        </div>
		      </div>
		      <div class="sidebar-header">
		        <div class="user-pic">
		          <img class="img-responsive img-rounded" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
		            alt="User picture">
		        </div>
		        <div class="user-info">
		          <span class="user-name">
		            <strong>염 종찬</strong>
		          </span>
		          <span class="user-role">Administrator</span>
		          <span class="user-status">
		            <i class="fa fa-circle"></i>
		            <span>Online</span>
		          </span>
		        </div>
		      </div>
		      <div class="sidebar-search">
		        <div>
		          <div class="input-group">
		            <input type="text" class="form-control search-menu" placeholder="Search...">
		            <div class="input-group-append">
		              <span class="input-group-text">
		                <i class="fa fa-search" aria-hidden="true"></i>
		              </span>
		            </div>
		          </div>
		        </div>
		      </div>
		      <div class="sidebar-menu">
		        <ul>
		          <li class="header-menu">
		            <span>관리자 메뉴</span>
		          </li>
		          <li class="sidebar-dropdown">
		            <a href="">
		              <i class="fa fa-tachometer-alt"></i>
		              <span>사용자 정보 관리</span>
		            </a>
		            <div class="sidebar-submenu">
		              <ul>
		                <li>
		                  <a href="/Main#!/list">사용자 목록</a>
		                </li>
		                <li>
		                  <a href="/Main#!/apply">가입 신청현황</a>
		                </li>
		              </ul>
		            </div>
		          </li>
		        </ul>
		      </div> 
		      <div class="sidebar-menu">
		        <ul>
		          <li class="header-menu">
		            <span>Contents</span>
		          </li>
		          <li class="sidebar-dropdown">
		            <a href="#">
		              <i class="fa fa-tachometer-alt"></i>
		              <span>게시물</span>
		            </a>
		            <div class="sidebar-submenu">
		              <ul>
		                <li>
		                  <a href="#">공지사항</a>
		                </li>
		                <li>
		                  <a href="#">공개된 자료</a>
		                </li>
		              </ul>
		            </div>
		          </li>
		          <li class="sidebar-dropdown">
		            <a href="#">
		              <i class="fa fa-shopping-cart"></i>
		              <span>메모장</span>
		            </a>
		            <div class="sidebar-submenu">
		              <ul>
		                <li>
		                  <a href="#">Products</a>
		                </li>
		                <li>
		                  <a href="#">Orders</a>
		                </li>
		                <li>
		                  <a href="#">Credit cart</a>
		                </li>
		              </ul>
		            </div>
		          </li>
		          <li class="sidebar-dropdown">
		            <a href="#">
		              <i class="fa fa-book"></i>
		              <span>참조자료들</span>
		            </a>
		            <div class="sidebar-submenu">
		              <ul>
		                <li>
		                  <a href='https://pngtree.com/so/한국'>한국 png from pngtree.com</a>
		                </li>
		                <li>
		                  <a href='https://pngtree.com/so/벚꽃'>벚꽃 png from pngtree.com</a>
		                </li>
		                <li>
		                  <a href="#">Credit cart</a>
		                </li>
		              </ul>
		            </div>
		          </li>
		          <li class="header-menu">
		            <span>대화창</span>
		          </li>
		        </ul>
		      </div>
		    </div>
		    <div class="sidebar-footer">
		      <a>
		        <i class="fa fa-power-off"></i>
		      </a>
		    </div>
		  </nav>
		  <section class="page-content">
		  	<div style="height: 400px;"></div>
		  	<div data-ng-view>
		  	</div>
		  </section>
		</div>
	</body>
</html>