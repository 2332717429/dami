package com.zjl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class page<T> {
    private List<T> list;
    private Integer currentPage;//当前页
    private Integer totalPage;//总页数
    private Integer pageSize;//一页条数
    private Integer totalCount; //总条数
}
