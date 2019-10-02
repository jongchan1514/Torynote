<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <div class="container" style="color: #848CB5; margin-bottom: 20px;" data-ng-repeat="data1 in notice1 | orderBy : '-No'">
    	<div class="card flex-row flex-wrap">
    		<input type="hidden" value="{{data1.No}}">
    		<div class="card-footer w-100 text-muted" style="background-color: #ffe2ed">
            	{{data1.Title}}
        	</div>
        	<div class="card-block px-2">
            	<h4 class="card-title">{{data1.Nickname}}</h4>
            	<p class="card-text">{{data1.Notice}}</p>
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
	
	<button href="#myModal" id="openBtn" data-toggle="modal" class="btn btn-default">Modal</button>

	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
      		<div class="modal-content">
        		<div class="modal-header">
        			<h3 class="modal-title">제목</h3>
          			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        		</div>
        		<div class="modal-body">내용</div>
			    <div class="modal-footer">
			       <button type="button" class="btn btn-default " data-dismiss="modal">닫기</button>
			       <button type="button" class="btn btn-primary">저장 후 닫기</button>
			    </div>					
			</div>
		</div>
	</div>
			     