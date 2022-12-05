package com.zjl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zjl.pojo.type;
import com.zjl.utils.DBHelper;

public class typeMapper {
	private DBHelper db= new DBHelper();

	public List<type> getAll() {
		List<type> list=new ArrayList<type>();
		String sql="select * from type";
		ResultSet rSet=db.read(sql);
		try {
			while(rSet.next()) {
				list.add(new type(rSet.getInt(1),rSet.getString(2),rSet.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(rSet);
		}
		return list;
	}

}
