package org.swamp.frontend.views;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.sers.webutils.client.controllers.WebAppExceptionHandler;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.security.User;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.UserService;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.core.services.TransactionRecordService;
import org.swamp.backend.models.security.PermissionConstants;
import org.swamp.frontend.security.HyperLinks;

import com.googlecode.genericdao.search.Search;


@ManagedBean(name = "dashboard")
@ViewScoped
@ViewPath(path = HyperLinks.DASHBOARD)
public class Dashboard extends WebAppExceptionHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private User loggedinUser;
    
    Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
    private String searchTerm;
	private SortField selectedSortField;
	
	//getting total users
	private UserService userService;
	private int totalSystemAdmins;
	private int totalHardwareTechnicians;
	
	//getting total meters
	private MeterService meterService;
	private int totalMeters;
	private int totalSystemAdminMeters;
	
	//getting total customers
	private CustomerService customerService;
	private int totalCustomers;
	private int totalSystemAdminCustomers;
	
	//getting total money
	private TransactionRecordService transactionRecordService;
	private BigDecimal totalMoney;
	private BigDecimal totalAdminMoney;
    
    @SuppressWarnings("unused")
    private String viewPath;
    
    @PostConstruct
    public void init() {
        loggedinUser = SharedAppData.getLoggedInUser();
		this.userService = ApplicationContextProvider.getBean(UserService.class);
		this.meterService = ApplicationContextProvider.getBean(MeterService.class);
		this.customerService = ApplicationContextProvider.getBean(CustomerService.class);
		this.transactionRecordService = ApplicationContextProvider.getBean(TransactionRecordService.class);
		
		this.setTotalSystemAdmins(this.userService.getUsersByRoleName(PermissionConstants.SYSTEM_ADMINISTRATOR).size());
		this.setTotalHardwareTechnicians(this.userService.getUsersByRoleName(PermissionConstants.HARDWARE_TECHNICIAN).size());
		this.setTotalCustomers(this.customerService.countInstances(this.search));
		this.setTotalMeters(this.meterService.countInstances(this.search));
		this.setTotalSystemAdminCustomers(this.customerService.getSystemAdminCustomers(loggedinUser).size());
		this.setTotalSystemAdminMeters(this.meterService.getAdminMeters(loggedinUser).size());
		this.setTotalMoney(this.transactionRecordService.getTotalMoney());
		this.setTotalAdminMoney(this.transactionRecordService.getTotalAdminMoney(loggedinUser));
    }

	public User getLoggedinUser() {
        return loggedinUser;
    }

    public void setLoggedinUser(User loggedinUser) {
        this.loggedinUser = loggedinUser;
    }

    /**
     * @return the viewPath
     */
    public String getViewPath() {
        return Dashboard.class.getAnnotation(ViewPath.class).path();
    }

    /**
     * @param viewPath the viewPath to set
     */
    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

	/**
	 * 
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public int getTotalSystemAdmins() {
		return totalSystemAdmins;
	}

	public void setTotalSystemAdmins(int totalSystemAdmins) {
		this.totalSystemAdmins = totalSystemAdmins;
	}

	public int getTotalHardwareTechnicians() {
		return totalHardwareTechnicians;
	}

	public void setTotalHardwareTechnicians(int totalHardwareTechnicians) {
		this.totalHardwareTechnicians = totalHardwareTechnicians;
	}

	public MeterService getMeterService() {
		return meterService;
	}

	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public int getTotalMeters() {
		return totalMeters;
	}

	public void setTotalMeters(int totalMeters) {
		this.totalMeters = totalMeters;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public int getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public int getTotalSystemAdminMeters() {
		return totalSystemAdminMeters;
	}

	public void setTotalSystemAdminMeters(int totalSystemAdminMeters) {
		this.totalSystemAdminMeters = totalSystemAdminMeters;
	}

	public int getTotalSystemAdminCustomers() {
		return totalSystemAdminCustomers;
	}

	public void setTotalSystemAdminCustomers(int totalSystemAdminCustomers) {
		this.totalSystemAdminCustomers = totalSystemAdminCustomers;
	}

	public TransactionRecordService getTransactionRecordService() {
		return transactionRecordService;
	}

	public void setTransactionRecordService(TransactionRecordService transactionRecordService) {
		this.transactionRecordService = transactionRecordService;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getTotalAdminMoney() {
		return totalAdminMoney;
	}

	public void setTotalAdminMoney(BigDecimal totalAdminMoney) {
		this.totalAdminMoney = totalAdminMoney;
	}
}
