package com.src.java.crm.listerner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.src.java.crm.dao.CustomerDAO;
import com.src.java.crm.dao.GpsModuleDAO;
import com.src.java.crm.dao.GpsModuleVersionDetailDAO;
import com.src.java.crm.dao.GpsSimReleaseDAO;
import com.src.java.crm.dao.SeverityDAO;
import com.src.java.crm.dao.UserDAO;
import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.GpsModule;
import com.src.java.crm.dto.GpsModuleVersionDetail;
import com.src.java.crm.dto.GpsSimRelease;
import com.src.java.crm.dto.Severity;
import com.src.java.crm.dto.User;
import com.src.java.crm.utilities.DBUtil;

@WebListener
public class CRMContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			
			SeverityDAO severityDAO = new SeverityDAO(con);
			GpsModuleDAO gpsModuleDAO = new GpsModuleDAO(con);
			GpsSimReleaseDAO gpsSimReleaseDAO = new GpsSimReleaseDAO(con);
			GpsModuleVersionDetailDAO gpsModuleVersionDetailDAO = new GpsModuleVersionDetailDAO(con);
			UserDAO userdao = new UserDAO(con);
			CustomerDAO custDao = new CustomerDAO(con);
			
			List<Severity> severityList = severityDAO.getAllSeverity();
			List<GpsModule> moduleList = gpsModuleDAO.getAllModule();
			List<GpsSimRelease> simReleaseList = gpsSimReleaseDAO.getGpsSimReleases();
			List<User> userList = userdao.getUsersByRoleId(3);
			userList.addAll(userdao.getUsersByRoleId(4));
			List<User> customerList = userdao.getUsersByRoleId(2);
			List<User> employeeList = userdao.getUsersByRoleId(3);
			
			List<GpsModuleVersionDetail> moduleVersionDetalList = gpsModuleVersionDetailDAO.getAllModules();
			List<Customer> top10CustomerList = custDao.fetchTop10CustomerDetails();
			
			ServletContext context = sce.getServletContext();
			context.setAttribute("SeverityList", severityList);
			context.setAttribute("GpsModuleList", moduleList);
			context.setAttribute("GpsSimReleaseList", simReleaseList);
			context.setAttribute("EmployeeAndSalesList", userList);
			context.setAttribute("CustomerList", customerList);
			context.setAttribute("EmployeeList", employeeList);
			context.setAttribute("ModuleVersionDetalList", moduleVersionDetalList);
			context.setAttribute("Top10CustomerList", top10CustomerList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
