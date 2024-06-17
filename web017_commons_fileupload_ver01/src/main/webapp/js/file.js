window.onload = function() {
	document.forms[0].onsubmit = function(event) {
		console.log("form submit 이벤트");
		event.preventDefault();

		var imgFile = document.getElementById("imgFile");
		var fileRegEx = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
		var maxSize = 5 * 1024 * 1024;
		var fileSize;

		if (imgFile.value == "") {
			alert("첨부파일은 필수");
			imgFile.focus();
			return;
		}

		console.log("file");

		//files의 모든 타입은 file인 객체로 되어 있다. 
		//index를 통해 해당 값을 확인해 줘야 한다. 
		console.log(imgFile.value); //미리보기
		console.log(imgFile.files[0].name);
		console.log(imgFile.files[0].size);
		
		fileSize = imgFile.files[0].size

		if (!imgFile.value.match(fileRegEx)) {
			alert("이미지 파일만 업로드 가능합니다.");
			return;
		} else if (maxSize < fileSize) {
			alert("파일 사이즈는 5MB까지만 가능합니다.");
			return;
		}
		document.forms[0].submit();

	}
}