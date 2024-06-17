package com.min.edu.vo;

import java.util.Date;

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
public class FileBoardVo {
	private int f_seq;
	private int b_seq;
	private String writer;
	private String origin_fname;
	private String stored_fname;
	private int file_size;
	private Date f_regdate;
	private String f_delflag;
}
