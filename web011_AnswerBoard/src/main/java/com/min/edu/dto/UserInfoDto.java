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
public class UserInfoDto implements Serializable{

	private static final long serialVersionUID = 7075967693976368450L;

	private String id;
	private String name;
	private String email;
	private String auth;
	private String enable;
	private String joindate;
}
