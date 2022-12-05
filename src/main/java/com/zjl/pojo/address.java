package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class address {
    private Integer aid;
    private Integer uid;
    private String aname;
    private String aphone;
    private String adetail;
    private Integer astate;
}
