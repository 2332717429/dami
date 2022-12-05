package com.zjl.mapper;

import com.zjl.pojo.address;
import com.zjl.utils.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class addressMapper {
    private static DBHelper db=new DBHelper();

    /**
     * 根据uid查询address
     * @param uid
     * @return
     */
    public List<address> getAllByUid(int uid){
        List<address> list=new ArrayList<address>();
        String sql="select * from address where u_id="+uid;
        ResultSet read = db.read(sql);
        try {
            while(read.next()){
                list.add(new address(read.getInt("a_id"),read.getInt("u_id"), read.getString("a_name"),
                        read.getString("a_phone"), read.getString("a_detail"), read.getInt("a_state") ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 根据aid删除address表中的字段
     * @param aid
     * @return
     */
    public int delAllByAid(int aid){
        String sql="delete from address where a_id="+aid;
        return db.zsg(sql);
    }

    /**
     * address的修改
     * @param address
     * @return
     */
    public  int updAllByAid(address address){
        String sql="update address set a_name='"+address.getAname()+"' ,a_phone='"+address.getAphone()+"',a_detail='"+address.getAdetail()+
                "' where a_id='"+address.getAid()+"' and u_id='"+address.getUid()+"'";
        return db.zsg(sql);
    }

    /**
     * 新增收货地址
     * @param cname
     * @param cphone
     * @param adetail
     * @param uid
     * @return
     */
    public int addAll(String cname,String cphone,String adetail,int uid){
        String sql="insert into address values(0,"+uid+",'"+cname+"','"+cphone+"','"+adetail+"',0)";
        return db.zsg(sql);
    }

    /**
     *  设置所有uid的字段为0
     * @param uid
     * @return
     */
    public int updStatusByAid(int uid){
        String sql="update address set a_state=0 where u_id="+uid;
        return db.zsg(sql);
    }

    /**
     *  设置所选的字段为1
     * @param uid
     * @param aid
     * @return
     */
    public int updDefaultStatusByAid(int uid,int aid){
         updStatusByAid(uid);
         String sql="update address set a_state=1 where a_id="+aid;
        return db.zsg(sql);

    }
}
