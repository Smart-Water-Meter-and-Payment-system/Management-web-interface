package org.swamp.frontend.views.swamp.customers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.sers.webutils.client.views.presenters.PaginatedTableView;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.core.utils.CustomSearchUtils;
import org.swamp.backend.models.customer.Customer;
import org.swamp.backend.models.meter.Meter;
import org.swamp.backend.models.security.PermissionConstants;
import org.swamp.frontend.security.HyperLinks;

import com.googlecode.genericdao.search.Search;

@ManagedBean(name="customerView")
@SessionScoped
@ViewPath(path= HyperLinks.CUSTOMER_VIEW)
public class CustomerView extends PaginatedTableView<Customer, CustomerView, CustomerView> {

	private static final long serialVersionUID = 1L;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
	private List<SortField> sortFields;
	private SortField selectedSortField;
	private String searchTerm;
	private MeterService meterService;
	private CustomerService customerService;
	private List<Meter> meters, selectedMeters = new ArrayList<Meter>();
	private BigDecimal householdBalance;
	private Date fromDate, toDate;

	@PostConstruct
	public void init() {
		this.setMeterService(ApplicationContextProvider.getBean(MeterService.class));
		this.setCustomerService(ApplicationContextProvider.getBean(CustomerService.class));
		this.sortFields = Arrays.asList(new SortField[] {new SortField("Phone Number Asc", "phoneNumber", false), 
				new SortField("Phone Number Desc", "phoneNumber", true)});
		this.meters = this.meterService.getAllInstances();
		this.householdBalance = null;
		this.fromDate = this.toDate = null;
		super.setMaximumresultsPerpage(10);
	}
	
	@Override
	public void reloadFromDB(int arg0, int arg1, Map<String, Object> arg2) throws Exception {
		this.search = CustomSearchUtils.generateSearchObjectForCustomers(searchTerm, selectedSortField, 
				this.selectedMeters, householdBalance, fromDate, toDate);
		if(SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SYSTEM_ADMINISTRATOR) && 
				!SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SUPER_ADMINISTRATOR))
			this.search.addFilterIn("meterId", this.meterService.getAdminMeters(SharedAppData.getLoggedInUser()));
		super.setDataModels(this.customerService.getInstances(search, arg0, arg1));
	}
	
	@Override
	public void reloadFilterReset() {
		this.search = CustomSearchUtils.generateSearchObjectForCustomers(searchTerm, selectedSortField, 
				this.selectedMeters, householdBalance, fromDate, toDate);
		if(SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SYSTEM_ADMINISTRATOR) && 
				!SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SUPER_ADMINISTRATOR))
			this.search.addFilterIn("meterId", this.meterService.getAdminMeters(SharedAppData.getLoggedInUser()));
		super.setTotalRecords(this.customerService.countInstances(search));
	}

	@Override
	public List<ExcelReport> getExcelReportModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
	
	public List<SortField> getSortFields() {
		return sortFields;
	}

	public void setSortFields(List<SortField> sortFields) {
		this.sortFields = sortFields;
	}

	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public MeterService getMeterService() {
		return meterService;
	}

	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public List<Meter> getMeters() {
		return meters;
	}

	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	public List<Meter> getSelectedMeters() {
		return selectedMeters;
	}

	public void setSelectedMeters(List<Meter> selectedMeters) {
		this.selectedMeters = selectedMeters;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public BigDecimal getHouseholdBalance() {
		return householdBalance;
	}

	public void setHouseholdBalance(BigDecimal householdBalance) {
		this.householdBalance = householdBalance;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
}
