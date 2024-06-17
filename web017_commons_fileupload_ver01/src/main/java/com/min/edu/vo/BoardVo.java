package com.min.edu.vo;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardVo {
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	
	private List<FileBoardVo> filevo; //일대다를 위한.../ 파일 여러개 업로드 위해
}
