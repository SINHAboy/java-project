package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.src.java.crm.dao.GpsModuleDAO;
import com.src.java.crm.dao.SeverityDAO;
import com.src.java.crm.dto.GpsModule;
import com.src.java.crm.dto.Severity;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;

/**
 * This service is admin activities
 * 
 * @author user
 *
 */
public class GPSAdminService extends AbstractGPSUserService {
	public Severity saveAndGetSeverity(String sevDescription) {
		Severity sev = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				SeverityDAO sDAO = new SeverityDAO(con);
				sDAO.addSeverity(sevDescription);
				con.commit();

				sev = sDAO.getSeverityByName(sevDescription);

			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to save and get severity");
		}

		return sev;
	}	
	
	public GpsModule saveAndGetModule(String sevDescription) {
		GpsModule mod = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				GpsModuleDAO mDAO = new GpsModuleDAO(con);
				mDAO.addModule(sevDescription);
				con.commit();

				mod = mDAO.getModuleByName(sevDescription);

			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to save and get module");
		}

		return mod;
	}

	public void removeSeverity(int tid) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				SeverityDAO mDAO = new SeverityDAO(con);
				mDAO.deleteSeverityById(tid);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to delete severity");
		}		
	}
	
	public void removeModule(int tid) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				GpsModuleDAO mDAO = new GpsModuleDAO(con);
				mDAO.deleteModuleById(tid);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to delete module");
		}		
	}

}
