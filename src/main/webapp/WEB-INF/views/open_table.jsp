<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <div data-ng-hide=boolean>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2><b>자유 게시판</b></h2></div>
                    <div class="col-sm-4">
                        <div class="search-box">
                            <input type="text" class="form-control" placeholder="Search&hellip;" data-ng-model="search" value="{{search}}">
                         	<div class="input-group-append" >
		                		<i class="fa fa-search" aria-hidden="true" data-ng-click="table_search()"></i>
		            		</div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목 </th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>                        
                    </tr>
                </thead>
                <tbody>
                    <tr data-ng-click="table_view($index)" data-ng-repeat="val in opentable | orderBy : '-No' | limitTo  : 10 " data-toggle="modal" href="#myModal">
                        <td>{{val.No}}</td>
                        <td>{{val.Title}}</td>
                        <td>{{val.Nickname}}</td>
                        <td style="font-size: 0.8em;">{{val.TIME}}</td>
                        <td>{{val.col}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <button type="button" class="btn btn-warning" data-ng-click="table_write()">글작성</button>
    </div>
    <nav aria-label="Pagination alignment">
	  <ul class="pagination justify-content-center">
	    <li class="page-item"><button class="page-link" data-ng-model="table_page_prev" data-ng-disabled="table_page_prev" data-ng-click="table_page_prev.data()">이전페이지</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "table_pageSize >= 1" data-ng-click="table_page(currentPage)">{{currentPage}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "table_pageSize >= 2" data-ng-click="table_page(currentPage+10)">{{currentPage+1}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "table_pageSize >= 3" data-ng-click="table_page(currentPage+20)">{{currentPage+2}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "table_pageSize >= 4" data-ng-click="table_page(currentPage+30)">{{currentPage+3}}</button></li>
	    <li class="page-item"><button class="page-link" data-ng-if = "table_pageSize >= 5" data-ng-click="table_page(currentPage+40)">{{currentPage+4}}</button></li> 
	    <li class="page-item"><button class="page-link" data-ng-model="table_page_next" data-ng-disabled="table_page_next" data-ng-click="table_page_next.data()">다음페이지</button></li>
	  </ul>
	</nav>
	
		<div class="modal fade" id="myModal">
		<div class="modal-dialog" style="max-width: 100%; width: auto; display: table;">
      		<div class="modal-content">
        		<div class="modal-header">
        			<h3 id="viewtitle" class="modal-title" data-ng-model="table_views.Title">{{table_views.Title}}</h3>
        			<input type="hidden" value="{{table_views.No}}">
        			<input type="hidden" value="{{table_views.Nickname}}">
          			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        		</div>
        		<div id="viewval" class="modal-body"data-ng-model="table_views" data-ng-bind-html="table_views.Tags"></div>
			    <div class="modal-footer">
			       <button type="button" class="btn btn-warning" data-ng-click="table_alt()" data-toggle="modal"  href="#myModal">수정</button>
			       <button type="button" class="btn btn-primary" data-ng-click="table_delete()" data-toggle="modal"  href="#myModal">삭제</button>
			       <button type="button" class="btn btn-danger" data-toggle="modal"  href="#myModal">닫기</button>
			    </div>					
			</div>
		</div>
	</div>
	</div>
	
	 <form data-ng-hide=!boolean>
	 <input type="text" name="tiqqtle" style="width: 100%;" placeholder="제목을 입력해주세요" data-ng-model="table_views.Title" value="{{table_views.Title}}">
    	<input type="hidden" name="no" value="{{views.No}}">
    	<br>
    	<br>
        <textarea name="table_editor" id="table_editor" rows="10" cols="80" ></textarea>
        <br>
        <button data-ng-click="table_insert()">입력</button>
        <button data-ng-click="table_cancel()">취소</button>
   	</form>   