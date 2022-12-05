package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class product {
    private Integer pid;
    private Integer tid;
    private String pname;
    private Date ptime;
    private String pimage;
    private Double pprice;
    private Integer pstate;
    private String pinfo;
}
