

let isCtrl = false; //ctrl 키의 조합키 판단을 위한 값
let isShow = true;//submit은 페이지 벗어남 알림

/*로드 되기 전*/
window.onbeforeunload = function() {
	if (isShow) {
		return "화면에서 벗어납니다.";
	}
}


document.addEventListener("keydown", function(e) {
	if (e.keyCode === 17) {
		isCtrl = true;
	}

	if ((e.keyCode === 116) || (e.keyCode === 82 && isCtrl) || (e.keyCode === 78 && isCtrl)) {
		e.preventDefault();
		e.stopPropagation();
		return false;
		console.log(e.keyCode === 116 ? "F5" : (e.keyCode === 82 ? "Ctrl+R" : "Ctrl+N"));
	}	
});

//preventDefault(); -> html 함수 막음
//e.stopPropagation();-> onclick 이벤트 막음

function validateForm() {
	console.log("유효성검사: XSS 처리");
	var form = document.forms[0];

	var title = document.getElementById("title");
	var content = document.getElementById("content");

	console.log(title.value, content.value);
	if (title.value == "" || content.value == "") {
		alert("필수값을 입력해주세요!");
	} else {
		isShow = false; //submit을 동작시킬 땐 alert 발생하지 않음
		
		var str=content.value;
		str=str.replace(/\r\n|\r|\n|\n\r/gim,"<br>");
		str=str.replace(/</gim,"&lt;");
		str=str.replace(/>/gim,"&gt;");
		str=str.replace(/\'/gim,"&#39;");
		document.getElementById("converView").innerHTML=str;
		
		content.value=str;
		form.submit();
	}
}