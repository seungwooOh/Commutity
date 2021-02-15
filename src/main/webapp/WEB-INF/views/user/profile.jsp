<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div>
		<img alt="프로필 이미지" src="/res/img/${requestScope.data.profileImg == null ? 'profile.jpg' : requestScope.data.profileImg}">
	</div>
	<div>
		프로필 이미지 업로드 : <input type="file" id="profileImg" accept="image/*">
		<input type="button" value="업로드" onclick="profileUpload()">
	</div>
	<div>아이디 : ${requestScope.data.userId}</div>
	<div>이름 : ${requestScope.data.nm}</div>
	<div>성별 : ${requestScope.data.gender == 0 ? '여' : '남'}</div>
	<div>가입일 : ${requestScope.data.regDt}</div>
</div>

<script src="/res/js/user/profile.js"></script>