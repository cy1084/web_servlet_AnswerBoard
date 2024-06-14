package com.min.edu.dto;

import java.io.Serializable;

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
public class AnswerboardDto implements Serializable{

	private static final long serialVersionUID = 4929768572262021453L;

	private int seq    ;
	private String id     ;
	private String title  ;
	private String content;
	private int ref    ;
	private int step   ;
	private int depth  ;
	private String regdate;
	private String delflag;
	
	private String name;
	
}
