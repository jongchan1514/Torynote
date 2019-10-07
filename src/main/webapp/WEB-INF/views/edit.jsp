<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <form>
    	<input type="text" name="title" style="width: 100%;" placeholder="제목을 입력해주세요" data-ng-model="edit_title">
    	<br>
    	<br>
        <textarea name="editor" id="editor" rows="10" cols="80" ></textarea>
        <br>
        <button type="button" data-ng-click="insert()">입력</button>
        <button type="button" data-ng-click="cancel()">취소</button>
   	</form>
