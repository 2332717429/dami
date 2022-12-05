package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class user {
	private Integer uid;
	private String uname;
	private String upassword;
	private String uemail;
	private String usex;
	private Integer ustatus;
	private String ucode;

	public user(String uname, String upassword) {
		this.uname = uname;
		this.upassword = upassword;
	}
}
