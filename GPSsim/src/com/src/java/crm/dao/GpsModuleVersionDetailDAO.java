package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.GpsModuleVersionDetail;
import com.src.java.crm.exceptions.DAOException;

public class GpsModuleVersionDetailDAO {
	private Connection con;

	public GpsModuleVersionDetailDAO(Connection con) {
		this.con = con;
	}

	public List<GpsModuleVersionDetail> getAllModules() {
		List<GpsModuleVersionDetail> verList = new ArrayList<GpsModuleVersionDetail>();
		
		GpsModuleVersionDetail moduleVersionDetail = null;
		try {
			String sql = "select * from gpsmodules_details order by version";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				moduleVersionDetail = new GpsModuleVersionDetail();
				moduleVersionDetail.setId(rs.getInt("id"));
				moduleVersionDetail.setModuleId(rs.getInt("moduleId"));
				moduleVersionDetail.setVersion(rs.getFloat("version"));
				moduleVersionDetail.setReleatseDate(rs.getDate("releaseDate"));
				
				verList.add(moduleVersionDetail);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to verion details, Please contact admin");
		}
		
		return verList;
	}
}
