package com.zjl.mapper;

import com.zjl.pojo.cart;
import com.zjl.pojo.product;
import com.zjl.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cartMapper {
    private static DBHelper db = new DBHelper();

    /**
     * 加入购物车
     *
     * @param cart
     * @return
     */
    public int save(cart cart) {
        String sql = "insert into cart values(0,'" + cart.getUid() + "','" + cart.getPid() + "','" + cart.getCcount() + "','" + cart.getCnum() + "')";
        int zsg = db.zsg(sql);
        return zsg;
    }

    /**
     *
     * @param pid
     * @param uid   查询购物车内是否有数据
     * @return
     */
    public cart cart(int pid, int uid) {
        cart cart = new cart();
        String sql = "select * from cart where u_id='" + uid + "' and p_id=" + pid;
        ResultSet read = db.read(sql);
        try {
         if (read.next()){
            cart.setCid(read.getInt(1));
            cart.setUid(read.getInt(2));
            cart.setPid(read.getInt(3));
            cart.setCcount(read.getDouble(4));
            cart.setCnum(read.getInt(5));
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    /**
     *  查询购物车
     * @param cart
     * @return
     */
    public int setCart(cart cart){
        String sql="update cart set c_num='"+cart.getCnum()+"',c_count='"+cart.getCcount()+"' where c_id="+cart.getCid();
        int zsg = db.zsg(sql);
        return zsg;
    }

    /**
     * 根据uid连表查询所有购物车商品
     * @param uid
     * @return
     */
    public List<cart> getCart(int uid){
        List<cart> list =new ArrayList<cart>();
        String sql="select * from cart c inner join product p on c.p_id=p.p_id where c.u_id="+uid;

        ResultSet read = db.read(sql);
        try {
            while(read.next()){
                cart cart = new cart();
                cart.setCid(read.getInt(1));
                cart.setUid(read.getInt(2));
                cart.setPid(read.getInt(3));
                cart.setCcount(read.getDouble(4));
                cart.setCnum(read.getInt(5));
                product  product=new product(read.getInt("p_id"),read.getInt("t_id"), read.getString("p_name"),read.getDate("p_time")
                        ,read.getString("p_image"), read.getDouble("p_price"), read.getInt("p_state"), read.getString("p_info") );
                cart.setProduct(product);
                list.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 删除购物车中的商品
     * @param cid
     * @return
     */
    public int deleteCart(int cid){
        String sql="delete from cart where c_id="+cid;
        int zsg = db.zsg(sql);
        return zsg;
    }

    /**
     * 根据pid修改数量和总计
     * @param pid
     * @param cnum
     * @param counts
     * @return
     */
   public int setCnumAndCounts(int pid,int cnum,double counts){
        String sql="update cart set c_num= '"+cnum+"',c_count='"+(counts*cnum)+"'where p_id="+pid;
       return db.zsg(sql);
   }

    /**
     * 根据cid查询pid
     * @param cid
     * @return
     */
    public cart getpid(int cid){
        cart cart=new cart();
        String sql="select p_id from cart where c_id ="+cid;
        ResultSet read = db.read(sql);
        try {
            while(read.next()){
                cart.setPid(read.getInt("p_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    public void deleteAll(int clear) {
        String sql="delete from cart where u_id="+clear;
        db.zsg(sql);
    }
}