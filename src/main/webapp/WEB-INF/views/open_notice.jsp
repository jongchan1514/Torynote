<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div data-ng-hide=boolean>
      <div class="container" style="color: #848CB5; margin-bottom: 20px;" data-ng-click="view($index)" data-ng-repeat="val in notice | orderBy : '-No'" data-toggle="modal" href="#myModal" >
    	<div class="card flex-row flex-wrap">
    		<input type="hidden" value="{{val.No}}">
    		<div class="card-footer w-100 text-muted" style="background-color: #ffe2ed">
            	{{val.Nickname}}
        	</div>
        	<div class="card-block px-2">
            	<h4 class="card-title">{{val.Title}}</h4>
            	<p class="card-text">{{val.Notice}}</p>
        	</div>
        	<div class="w-100"></div>
    	</div>
    </div>
    <br>
	<nav aria-label="Pagination alignment">
	  <ul class="pagination justify-content-center">
	    <li class="page-item "><a class="page-link" href="">이전페이지</a></li>
	    <li class="page-item"><a class="page-link" href="">1</a></li>
	    <li class="page-item"><a class="page-link" href="">2</a></li>
	    <li class="page-item"><a class="page-link" href="">3</a></li>
	    <li class="page-item"><a class="page-link" href="">4</a></li>
	    <li class="page-item"><a class="page-link" href="">다음페이지</a></li>
	  </ul>
	</nav>
	
	<div class="modal fade" id="myModal">
		<div class="modal-dialog" style="max-width: 100%; width: auto; display: table;">
      		<div class="modal-content">
        		<div class="modal-header">
        			<h3 id="viewtitle" class="modal-title" data-ng-model="views">{{views.Title}}</h3>
        			<input type="hidden" value="{{views.No}}">
        			<input type="hidden" value="{{views.Nickname}}">
          			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        		</div>
        		<div id="viewval" class="modal-body"data-ng-model="views" data-ng-bind-html="views.Tags"></div>
			    <div class="modal-footer">
			       <button type="button" class="btn btn-primary" data-ng-click="alt()" data-toggle="modal"  href="#myModal">수정</button>
			       <button type="button" class="btn btn-primary" data-ng-click="delete_data()" data-toggle="modal"  href="#myModal">삭제</button>
			       <button type="button" class="btn btn-primary" data-toggle="modal"  href="#myModal">닫기</button>
			    </div>					
			</div>
		</div>
	</div>
</div>

			     