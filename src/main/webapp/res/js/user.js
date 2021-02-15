function chkItem(ele, nm) {
	if(!ele.value) {
		alert(`${nm}을(를) 작성해 주세요.`);
		ele.focus();		
		return true;
	}
}

var joinBtnElem = document.querySelector('#joinBtn')
if(joinBtnElem) {
	var frmElem = document.querySelector('#frm')
	var userIdElem = frmElem.userId
	var userPwElem = frmElem.userPw
	var userPwReElem = frmElem.userPwRe
	var nmElem = frmElem.nm
	var genderElem = frmElem.gender
	
	function eleChk () { //이상있으면 true, 이상없으면 false
		if(chkItem(userIdElem, 'Id') 
			|| chkItem(userPwElem, 'Pw') 
			|| chkItem(nmElem, '이름')) {
			return true
		} else if(userPwElem.value !== userPwReElem.value) {
			alert('비밀번호를 확인해 주세요.');
			userPwElem.focus()
			return true
		}
		return false
	}
	
	function ajax () {
		var param = {			
			userId: userIdElem.value,
			userPw: userPwElem.value,
			nm: nmElem.value,
			gender: genderElem.value
		}
		
		fetch('/user/join', {
			method: 'post',
			headers: {
            	'Content-Type': 'application/json',
        	},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			proc(myJson)			
		})
	}
	
	function proc (myJson) {
		if(myJson.result === 0) { //회원가입 실패 
			alert('회원가입에 실패하였습니다.')
			return
		}
		//회원가입 성공!
		alert('회원가입을 축하합니다')
		location.href = '/user/login'
	}
	
	joinBtnElem.addEventListener('click', function() {		
		if(eleChk()) { return }
		ajax()		
	})
	
}

var loginBtnElem = document.querySelector('#loginBtn')
if(loginBtnElem) {
	var frmElem = document.querySelector('#frm')
	var userIdElem = frmElem.userId
	var userPwElem = frmElem.userPw
	var errMsgElem = document.querySelector('#errMsg')
	function ajax() {
		if(userIdElem.value === '') {
			alert('아이디를 입력해 주세요')
			return
		} else if(userPwElem.value === '') {
			alert('비밀번호를 입력해 주세요')
			return
		}
		var param = {			
			userId: userIdElem.value,
			userPw: userPwElem.value
		}
		
		fetch('/user/login', {
			method: 'post',
			headers: {
            	'Content-Type': 'application/json',
        	},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			proc(myJson)
		})
	}
	
	function proc (myJson) {
		switch(myJson.result) {
			case 1:
				location.href = '/board/home'
			return
			case 2:
				alert('아이디를 확인해 주세요!!!')
			return
			case 3:
				alert('비밀번호를 확인해 주세요!!!')
			return
		}
	}
	loginBtnElem.addEventListener('click', ajax)
}


//아이디 체크
var chkIdBtnElem = document.querySelector('#chkIdBtn')
if(chkIdBtnElem) {

	function ajax () {
		var frmElem = document.querySelector('#frm')
		var userIdElem = frmElem.userId
		var userIdValue = userIdElem.value
		var idChkMsgElem = frmElem.querySelector('#idChkMsg')
	
		fetch(`/user/chkId/${userIdValue}`)
			.then(function (res) {
				return res.json()
			})
			.then(function(myJson) {
				console.log(myJson);
				if(myJson.result === 1) {
					idChkMsgElem.innerText = '중복된 아이디가 있습니다.';
				} else {
					idChkMsgElem.innerText = '사용할 수 있는 아이디 입니다.';
				}
			})
	}
	
	chkIdBtnElem.addEventListener('click', ajax)
}