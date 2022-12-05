package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class cart {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private product product;
    private Double ccount;
    private Integer cnum;
}
