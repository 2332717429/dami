package com.zjl.mapper;

import com.zjl.pojo.user;
import com.zjl.utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userMapper {
    private DBHelper db= new DBHelper();

    /**
     * 根据name查询数据库是否有字段
     * @param name
     * @return
     */
    public boolean getAllByName(String name){
        String sql="select * from user where u_name="+name;
        ResultSet read = db.read(sql);
        try {
            if(read.next()){
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 注册业务
     * @param user
     * @return
     */
    public int insert(user user) {
        String sql="insert into user values(0,'"+user.getUname()+"','"+user.getUpassword()+"','"+user.getUemail()+"','"+user.getUsex()+"','"+user.getUcode()+"','"+user.getUstatus()+"')";
      return db.zsg(sql);
    }

    /**
     * 登录业务 查询是否登录
     * @param username
     * @param pwd
     * @return
     */
    public user login(String username, String pwd) {
        user user=null;
        String sql="select * from user where u_name='"+username+"'and u_password='"+pwd+"'";
        ResultSet read = db.read(sql);
            try {
                while(read.next()){
                user=new user(read.getInt(1), read.getString(2),read.getString(3),read.getString(4),
                        read.getString(5),read.getInt(6),read.getString(7));
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
    }
}
