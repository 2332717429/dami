package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orders {
    private String oid;
    private Integer uid;
    private Integer aid;
    private Double ocount;
    private String otime;
    private Integer ostate;
    private address address;
    private List<item> items;
}
