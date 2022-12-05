package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class item {
    private Integer iid;
    private String oid;
    private Integer pid;
    private Double icount;
    private Integer inum;
    private product product;
}
