<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<a href="/board/list?category=${requestScope.data.category}">리스트로
		돌아가기</a>
</div>

<c:if
	test="${sessionScope.loginUser.userPk == requestScope.data.userPk}">
	<div>
		<a
			href="/board/edit?category=${requestScope.data.category}&boardPk=${requestScope.data.boardPk}"><button>수정</button></a>
		<button id="btnDel">삭제</button>
	</div>
</c:if>

<div id="data" data-pk="${requestScope.data.boardPk}"
	data-category="${requestScope.data.category}">
	<div>번호 : ${requestScope.data.seq}</div>
	<div>조회수 : ${requestScope.data.hits}</div>
	<div>
		제목 :
		<c:out value="${requestScope.data.title }" />
	</div>
	<div>
		작성일시 :
		<c:out value="${requestScope.data.regDt }" />
	</div>
	<div>
		작성자 :
		<c:out value="${requestScope.data.writerNm }" />
	</div>
	<div>
		내용 :
		<c:out value="${requestScope.data.ctnt}" />
	</div>
</div>

<c:if test="${sessionScope.loginUser != null}">
	<div>
		<h4>댓글쓰기</h4>
		<form id="cmtFrm">
			<input type="text" name="ctnt" placeholder="댓글을 입력해 주세요">
			<input type="button" name="btn" value="댓글등록">
		</form>
	</div>
</c:if>

<div id="cmtList">
	
</div>

<script src="/res/js/board/detail.js"></script>