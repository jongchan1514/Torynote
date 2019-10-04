<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table table-striped table-bordered table-hover" style="text-align:center;">
  <thead>
    <tr>
      <th>계정명</th>
      <th>닉네임</th>
      <th>생년월일</th>
      <th>성별</th>
    </tr>
   </thead>
   <tbody>
    <tr data-ng-repeat="item in apply">
      <th>{{item.USER}}</th>
      <th>{{item.Nickname}}</th>
      <th>{{item.Age}}</th>
      <th>{{item.Sex}}</th>
    </tr>
   </tbody>
 </table>