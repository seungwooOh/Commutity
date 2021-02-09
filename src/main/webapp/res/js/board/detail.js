var data = document.querySelector('#data')
var btnDelElem = document.querySelector('#btnDel')
if (btnDelElem) {
	btnDelElem.addEventListener('click', function() {
		if (confirm('삭제하시겠습니까?')) {
			ajax()
		}
	})

	function ajax() {
		var { pk, category } = data.dataset
		// var {pk:a, category:b} = data.dataset
		fetch(`/board/del/${pk}`, {
			method: 'delete'
		})
	}

}

//-----------------댓글[start]------------------------//
var cmtListElem = document.querySelector('#cmtList') // 댓글리스트 나타나는 위치
var modalElem = document.querySelector('#modal')
var modCtntElem = document.querySelector('#modCtnt') // 수정 내용
var modBtnElem = document.querySelector('#modBtn')	// 수정버튼

if(modalElem) {
	var modalCloseElem = document.querySelector('#modClose')
	modalCloseElem.addEventListener('click', function() {
		modalElem.classList.add('hide')
	})
}

function selCmtList() {
	fetch(`/cmt?boardPk=${data.dataset.pk}`)
	.then(res => res.json())
	.then(myJson =>{
		console.log(myJson)
		clearView()
		createView(myJson)
	})
	
	function clearView() {
		cmtListElem.innerHTML = ''
	}
	
	function createView(myJson) {
		if(myJson.lenth === 0) {
			return
		}
		
		var tableElem = createTable()
		myJson.forEach(function(item){
			tableElem.append(createRecord(item))
		})
		
		cmtListElem.append(tableElem)
	}
	
	function createRecord(item) {	// 한줄 레코드 만드는 부분
		var tr = document.createElement('tr')
		var td_1 = document.createElement('td')
		var td_2 = document.createElement('td')
		var td_3 = document.createElement('td')
		
		td_1.innerText = item.ctnt
		td_2.innerText = item.writerNm
		
		// 자신이 쓴 글이라면 삭제, 수정버튼 추가
		var loginUserPk = parseInt(data.dataset.loginuserpk)
		if(loginUserPk === item.writerPk) {
			// 삭제 처리
			function delAjax() {
				fetch(`/cmt?boardPk=${item.boardPk}&seq=${item.seq}`, {
					method: 'delete'
				}).then(res => res.json())
				.then(myJson => {
					if(myJson === 1) {
						selCmtList()
					} else {
						alert('삭제를 실패하였습니다.')
					}
				})
			}
			
			// 수정처리
			function modAjax(param) {
				fetch('/cmt', {
					method: 'put',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(param)
				}).then(res => res.json())
				.then(myJson => {
					if(myJson === 1) {
						modalElem.classList.add('hide')
						selCmtList()
					} else {
						alert('수정을 실패하였습니다.')
					}
				})
			}
			
			var delBtn = document.createElement('input')
			delBtn.type = 'button'
			delBtn.value = '삭제'
			delBtn.addEventListener('click', function() {
				if(confirm('삭제를 하시겠습니까?')) {
					delAjax()
				}
			})
			
			var editBtn = document.createElement('input')
			editBtn.type = 'button'
			editBtn.value = '수정'
			editBtn.addEventListener('click', function() {
				modCtntElem.value = item.ctnt
				modalElem.classList.remove('hide')
				
				modBtnElem.onclick = function() {
					var param = {
						boardPk: item.boardPk,
						seq: item.seq,
						ctnt: modCtntElem.value
					}
					
					modAjax(param)
				}
			})
			
			td_3.append(delBtn)
			td_3.append(editBtn)
			
		}
		
		tr.append(td_1)
		tr.append(td_2)
		tr.append(td_3)
		
		return tr
	}
	
	function createTable() {
		var tableElem = document.createElement('table')
		tableElem.innerHTML = `
		<tr>
			<th>내용</th>
			<th>작성자</th>
			<th>비고</th>
		</tr>`
		return tableElem
	}
}
selCmtList()

// 댓글등록
var cmtFrmElem = document.querySelector('#cmtFrm')
if (cmtFrmElem) {
	
	cmtFrmElem.onsubmit = function(e) {
		e.preventDefault()
	}
	
	var txtElem = cmtFrmElem.ctnt
	var btnElem = cmtFrmElem.btn
	btnElem.addEventListener('click', ajax)
	
	function ajax() {
		var txtVal = txtElem.value
		if (txtVal === '') {
			alert('댓글을 입력해 주세요')
			return
		}

		var param = {
			boardPk: data.dataset.pk,
			ctnt: txtVal
		}

		fetch('/cmt', {
			method: 'post',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(param)
		}).then(res => {
			res.json().then(myJson => {
				if(myJson === 1) {
					selCmtList()
				} else {
					alert('댓글 등록에 실패하였습니다.')
				}
			})
		})
	}
}