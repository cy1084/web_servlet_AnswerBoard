package com.min.edu.comm;

public class PhotoBean {
	
	private int depth;
	
	public void setDepth(int depth) {
		this.depth = depth;
	}


	//get 메소드는 argument가 없어야 됨!!!
	public String getPhoto() {
		String replyStr = "";
		String img = "<img alt='답글' src='./img/image.png'>"; //html은 ''로 인식
		String blank = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		for (int i = 0; i < depth; i++) {
			replyStr += blank;
		}

		return (depth > 0) ? replyStr + img : replyStr;
	}
}
