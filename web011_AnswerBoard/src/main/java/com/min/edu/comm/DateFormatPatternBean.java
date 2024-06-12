package com.min.edu.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//boardlist에 있는 것 빈으로 만들기!
public class DateFormatPatternBean {

	private String oldDate;

	//값을 입력할 수 있는 set 메소드
	public void setOldDate(String oldDate) {
		this.oldDate = oldDate; 
	}

	//기능이 부여되어 있는 get 메소드
	public String getDateFormatPattern() {
		
		Date cDate = null;
		Date nowDate = null;
		SimpleDateFormat sdf2 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			cDate = sdf.parse(oldDate); // regDate를 java.util.Date 타입으로 변경

			nowDate = new Date(); // 현재 날짜
			sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일"); // 변경할 출력의 형태
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sdf2.format(cDate).compareTo(sdf2.format(nowDate)) == 0 ? "오늘 작성" : sdf2.format(cDate);
	}

}
