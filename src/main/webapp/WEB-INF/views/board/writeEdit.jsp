<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form action="/board/${requestScope.data == null ? 'write' : 'edit'}" method="post">
		<div>
			<input type="hidden" name="category" value="${param.category}">
			<input type="hidden" name="boardPk" value="${requestScope.data == null ? '0' : requestScope.data.boardPk}">
		</div>
		<div>
			<input type="text" name="title" placeholder="제목" value="${requestScope.data.title}" required>
		</div>
		<div>
			<textarea name="ctnt" placeholder="내용" required>${requestScope.data.ctnt}</textarea>
		</div>
		<div>
			<input type="submit" value="${requestScope.data == null ? '등록' : '수정'}">
			<input type="reset" value="리셋">
		</div>
	</form>
</div>