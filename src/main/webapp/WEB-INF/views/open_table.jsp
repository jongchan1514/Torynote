<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2><b>자유 게시판</b></h2></div>
                    <div class="col-sm-4">
                        <div class="search-box">
                            <input type="text" class="form-control" placeholder="Search&hellip;">
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
                    <tr>
                        <td>1</td>
                        <td>Thomas Hardy</td>
                        <td>89 Chiaroscuro Rd.</td>
                        <td>Portland</td>
                        <td>97219</td>
                    </tr>
                </tbody>
            </table>
			<button type="button" class="btn btn-primary">글작성</button>
			<button type="button" class="btn btn-warning">수정</button>
			<button type="button" class="btn btn-danger">삭제</button>
        </div>
    </div>
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