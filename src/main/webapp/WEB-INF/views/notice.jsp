<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div data-ng-hide=boolean>
      <div class="container" style="color: #848CB5; margin-bottom: 20px;" data-ng-click="view($index)" data-ng-repeat="val in notice | orderBy : '-No' | limitTo  : 5 " data-toggle="modal" href="#myModal" >
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
	    <li class="page-item"><button class="page-link" data-ng-model="page_prev.data" data-ng-disabled="page_prev.data" data-ng-click="page_prev()">이전페이지</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "pageSize >= 1" data-ng-click="test(currentPage)">{{currentPage}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "pageSize >= 2" data-ng-click="test(currentPage+5)">{{currentPage+1}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "pageSize >= 3" data-ng-click="test(currentPage+10)">{{currentPage+2}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "pageSize >= 4" data-ng-click="test(currentPage+15)">{{currentPage+3}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "pageSize >= 5" data-ng-click="test(currentPage+20)">{{currentPage+4}}</button></li> 
	    <li class="page-item"><button class="page-link" data-ng-model="page_next.data" data-ng-disabled="page_next.data" data-ng-click="page_next()">다음페이지</button></li>
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
	
	 <form data-ng-hide=!boolean>
	 <input type="text" name="tiqqtle" style="width: 100%;" placeholder="제목을 입력해주세요" data-ng-model="views.Title" value="{{views.Title}}">
    	<input type="hidden" name="no" value="{{views.No}}">
    	<br>
    	<br>
        <textarea name="alt" id="alt" rows="10" cols="80" ></textarea>
        <br>
        <button data-ng-click="alt_data()">입력</button>
        <button data-ng-click="cancel()">취소</button>
   	</form>
			     