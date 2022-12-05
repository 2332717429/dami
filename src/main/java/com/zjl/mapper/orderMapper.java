package com.zjl.mapper;

import com.zjl.pojo.*;
import com.zjl.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderMapper {
    private static DBHelper db=new DBHelper();
    /**
     * 根据uid查询地址表的全部信息
     * @param uid
     * @return
     */
    public List<address> getAddressByUid(int uid){
        List<address> list=new ArrayList<address>();
        String sql="select * from address where u_id="+uid;
        ResultSet read = db.read(sql);
        try {
          while(read.next()){
              list.add(new address(read.getInt("a_id"), read.getInt("u_id"),read.getString("a_name"),
                      read.getString("a_phone"),read.getString("a_detail"), read.getInt("a_state") ));
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 创建订单
     * @param orders
     * @return
     */
    public int createOrder(orders orders){
         String sql="insert into orders values('"+orders.getOid()+"',"+orders.getUid()+","+orders.getAid()+","+orders.getOcount()+",'"+orders.getOtime()+"',"+orders.getOstate()+")";
        return db.zsg(sql);
    }

    /**
     * 根据uid查询订单集合
     * @param uid
     * @return
     */
    public List<orders> getAllByUid(int uid){
        List<orders> list=new ArrayList<orders>();
        String sql="select orders.*,address.* from orders inner join address on orders.a_id=address.a_id where orders.u_id="+uid;
        ResultSet read = db.read(sql);
        try {
            while (read.next()){
                orders orders = new orders();
                orders.setOid(read.getString("o_id"));
                orders.setUid(read.getInt("u_id"));
                orders.setAid(read.getInt("a_id"));
                orders.setOcount(read.getDouble("o_count"));
                orders.setOtime(read.getString("o_time"));
                orders.setOstate(read.getInt("o_state"));
                address address = new address();
                address.setAdetail(read.getString("a_detail"));
                address.setAstate(read.getInt("a_state"));
                address.setAphone(read.getString("a_phone"));
                address.setAname(read.getString("a_name"));
                address.setAid(read.getInt("a_id"));
                address.setUid(read.getInt("u_id"));
                orders.setAddress(address);
                list.add(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public orders getOrderByOid(String oid){
        String sql="select orders.*,address.* from orders inner join address on orders.a_id=address.a_id where orders.o_id='"+oid+"'";
        ResultSet read = db.read(sql);
        orders orders = new orders();
        try {
            if(read.next()){
                orders.setOid(read.getString(1));
                orders.setUid(read.getInt(2));
                orders.setAid(read.getInt(3));
                orders.setOcount(read.getDouble(4));
                orders.setOtime(read.getString(5));
                orders.setOstate(read.getInt(6));
                address address = new address();
                address.setAdetail(read.getString("a_detail"));
                address.setAstate(read.getInt("a_state"));
                address.setAphone(read.getString("a_phone"));
                address.setAname(read.getString("a_name"));
                address.setAid(read.getInt("a_id"));
                address.setUid(read.getInt("u_id"));
                orders.setAddress(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public static List<item> getItemss(int uid,String oid){
        List<item> items=new ArrayList<item>();
        List<cart> cart = new cartMapper().getCart(uid);
        for (int i = 0; i < cart.size(); i++) {
            item item = new item();
            item.setOid(oid);
            item.setPid(cart.get(i).getPid());
            item.setInum(cart.get(i).getCnum());
            item.setIcount(cart.get(i).getCcount());
            item.setProduct(new pageMapper().getProduct(item.getPid()));
            int i1 = new orderMapper().saveItems(item);
            System.out.println(i1);
            items.add(item);
        }
        return items;
    }
    public int saveItems(item item){
        String sql="insert into item values (0,'"+item.getOid()+"',"+item.getPid()+","+item.getIcount()+","+item.getInum()+")";
        return db.zsg(sql);
    }
    public List<item> getItemList(String oid){
       List<item> items = new ArrayList<item>();
        String sql="select item.*,product.* from item inner join product  on item.p_id=product .p_id where item.o_id='"+oid+"'";
        ResultSet read = db.read(sql);
        try {
            while(read.next()){
                item item = new item();
                item.setIid(read.getInt(1));
                item.setOid(read.getString(2));
                item.setPid(read.getInt(3));
                item.setIcount(read.getDouble(4));
                item.setInum(read.getInt(5));
                product product1 = new product(read.getInt("p_id"), read.getInt("t_id"), read.getString("p_name"),
                        read.getDate("p_time"),read.getString("p_image"), read.getDouble("p_price"),
                        read.getInt("p_state"),read.getString("p_info"));
                item.setProduct(product1);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
    public void setStu(String oid){
        String sql="update orders set o_state=4 where o_id='"+oid+"'";
        db.zsg(sql);
    }
}
