package com.study.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.study.servlet.entity.Role;
import com.study.servlet.util.DBConnectionMgr;

public class RoleRepositoryImpl implements RoleRepository {
	private static RoleRepository instance;
	public static RoleRepository getInstance() {
		if(instance == null) {
			instance = new RoleRepositoryImpl();
		}
		return instance;
	}
	
	private DBConnectionMgr pool;
	
	public RoleRepositoryImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	
	@Override
	public Role checkRoleName(String rolename) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Role role = new Role();
		try {
			con = pool.getConnection();
			String sql = "SELECT \r\n"
					+ "	*\r\n"
					+ "FROM\r\n"
					+ "	chatting.role_mst rm\r\n"
					+ "where\r\n"
					+ "	role_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rolename);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				role = Role
						.builder()
						.roleid(rs.getInt(1))
						.roleName(rs.getString(2))
						.build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return role;
	}
}
