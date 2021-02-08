<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<form id="frm">
		<div>
			<input type="text" name="userId" placeholder="ID">
		</div>
		<div>
			<input type="password" name="userPw" placeholder="PASSWORD">
		</div>
		<div>
			<input type="button" value="Login" id="loginBtn">
		</div>
	</form>
	<div class="errMsg">
		${requestScope.errMsg}
	</div>
	<a href="/user/join">Join</a>
</div>