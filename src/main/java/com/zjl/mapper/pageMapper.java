package com.zjl.mapper;

import com.zjl.pojo.page;
import com.zjl.pojo.product;
import com.zjl.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pageMapper {
    DBHelper db=new DBHelper();//创建db工具类的实例

    /**
     * "select count(1) from product where t_id=?"
     * @param tid 根据tid来查询条数
     * @return 返回总条数
     */
    public int counts(Integer tid){
        String sql="select count(1) from product where t_id="+tid;
        ResultSet read = db.read(sql);
        int rows=0;
        try {
            while (read.next()){
                rows=read.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * "select * form product where t_id =?limit ?,?
     * @param tid 根据tid 查询
     * @param currentPage 当前页
     * @param pageSize 每页多少条
     * @return
     */
    public List<product> selPage(int tid,int currentPage,int pageSize){
        String sql="select * from product where t_id = "+tid+" limit "+currentPage+","+pageSize+"";
        ResultSet read = db.read(sql);
        ArrayList<product> products = new ArrayList<product>();
        try {
            while (read.next()){
                products.add(new product(read.getInt(1),read.getInt(2),read.getString(3),read.getDate(4)
                ,read.getString(5),read.getDouble(6),read.getInt(7),read.getString(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    /**
     *
     * @param tid 根据tid 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页多少条
     * @return
     */
    public page<product> setPage(int tid,int currentPage,int pageSize){
        int counts = counts(tid);
        List<product> products = selPage(tid, (currentPage-1)*pageSize, pageSize);
        page<product> productpage = new page<product>();
        productpage.setList(products);
        productpage.setTotalCount(counts);
        productpage.setCurrentPage(currentPage);
        productpage.setPageSize(pageSize);
        productpage.setTotalPage((int) Math.ceil((double) counts/pageSize));
        return productpage;
    }

    /**
     * 根据pid查询product表的所有字段
     * @param pid
     * @return
     */
    public product getProduct(int pid) {
        String sql="select * from product where p_id="+pid;
        product product = null;
        ResultSet read = db.read(sql);
        try {
            while(read.next()){
               product=new product(read.getInt(1),read.getInt(2), read.getString(3),read.getDate(4)
               ,read.getString(5), read.getDouble(6), read.getInt(7), read.getString(8) );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  product;
    }
}
