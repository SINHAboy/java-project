package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.GpsSimRelease;
import com.src.java.crm.exceptions.DAOException;

public class GpsSimReleaseDAO {
	private Connection con;

	public GpsSimReleaseDAO(Connection con) {
		this.con = con;
	}

	public List<GpsSimRelease> getGpsSimReleases() {
		List<GpsSimRelease> simReleaseList = new ArrayList<GpsSimRelease>();
		
		GpsSimRelease gpsSim = null;
		try {
			String sql = "select * from gpssim_releases order by version";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				gpsSim = new GpsSimRelease();
				gpsSim.setId(rs.getInt("id"));
				gpsSim.setVersion(rs.getFloat("version"));
				gpsSim.setDate(rs.getDate("release_date"));
				
				simReleaseList.add(gpsSim);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to all gps sim releases, Please contact admin");
		}
		
		return simReleaseList;
	}
}
