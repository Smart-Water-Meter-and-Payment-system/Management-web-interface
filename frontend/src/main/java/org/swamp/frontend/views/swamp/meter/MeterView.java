package org.swamp.frontend.views.swamp.meter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;
import org.sers.webutils.client.views.presenters.PaginatedTableView;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.security.User;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.UserService;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.core.utils.CustomSearchUtils;
import org.swamp.backend.models.meter.Meter;
import org.swamp.backend.models.security.PermissionConstants;
import org.swamp.frontend.security.HyperLinks;
import org.swamp.frontend.security.UiUtils;

import com.googlecode.genericdao.search.Search;

@ManagedBean(name="meterView")
@SessionScoped
@ViewPath(path= HyperLinks.METER_VIEW)
public class MeterView extends PaginatedTableView<Meter, MeterView, MeterView> {

	private static final long serialVersionUID = 1L;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
	private List<SortField> sortFields;
	private SortField selectedSortField;
	private String searchTerm;
	private MeterService meterService;
	private List<User> users, selectedUsers, loadUsers;
	private User selectedUserId;
	private Meter selectedMeter;
	private String enteredIP;
	private BigDecimal longitude, latitude;
	private Date fromDate, toDate;

	@PostConstruct
	public void init() {
		this.setMeterService(ApplicationContextProvider.getBean(MeterService.class));
		this.sortFields = Arrays.asList(new SortField[] {new SortField("City Asc", "cityName", false), 
				new SortField("City Desc", "cityName", true)});
		try {
			this.users = ApplicationContextProvider.getBean(UserService.class).getUsers();
			this.loadUsers = ApplicationContextProvider.getBean(UserService.class).getUsers();
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}
		this.selectedUsers = new ArrayList<User>();
		this.longitude = null;
		this.latitude = null;
		this.fromDate = null;
		this.toDate = null;
		this.selectedMeter = new Meter();
		super.setMaximumresultsPerpage(10);
	}
	
	@Override
	public void reloadFromDB(int arg0, int arg1, Map<String, Object> arg2) throws Exception {
		List<String> userIds = new ArrayList<String>();
		for(User user : this.selectedUsers) {
			userIds.add(user.getId());
		}
		this.search = CustomSearchUtils.generateSearchObjectForMeters(searchTerm, selectedSortField, 
				userIds, longitude, latitude, fromDate, toDate);
		if(SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SYSTEM_ADMINISTRATOR) && 
				!SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SUPER_ADMINISTRATOR))
			this.search.addFilterEqual("userId", SharedAppData.getLoggedInUser());
		super.setDataModels(this.meterService.getInstances(search, arg0, arg1));
	}
	
	@Override
	public void reloadFilterReset() {
		List<String> userIds = new ArrayList<String>();
		for(User user : this.selectedUsers) {
			userIds.add(user.getId());
		}
		this.search = CustomSearchUtils.generateSearchObjectForMeters(searchTerm, selectedSortField, 
				userIds, longitude, latitude, fromDate, toDate);
		if(SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SYSTEM_ADMINISTRATOR) && 
				!SharedAppData.getLoggedInUser().hasPermission(PermissionConstants.SUPER_ADMINISTRATOR))
			this.search.addFilterEqual("userId", SharedAppData.getLoggedInUser());
		System.out.println(this.fromDate+"======="+this.toDate);
		super.setTotalRecords(this.meterService.countInstances(search));
	}
	

	public void loadSelectedMeter(Meter meter) {
		if(meter != null) {
			this.selectedMeter = meter;
			this.enteredIP = meter.getPublicIp();
			this.selectedUserId = meter.getUserId();
		}else {
			this.enteredIP = null;
			this.selectedUserId = null;
			this.selectedMeter = new Meter();
			try {
				this.setUsers(ApplicationContextProvider.getBean(UserService.class).getUsers());
			} catch (OperationFailedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getUsers());
		}
	}
	
	public void saveSelectedMeter() {
		try {
//			CityResponse response = meterService.getLocation(this.enteredIP);
			this.selectedMeter.setUserId(this.selectedUserId);
			this.selectedMeter.setPublicIp(this.enteredIP);
//			this.selectedMeter.setCountryName(response.getCountry().getName());
//			this.selectedMeter.setCityName(response.getCity().getName());
//			this.selectedMeter.setLatitude(BigDecimal.valueOf(response.getLocation().getLatitude()));
//			this.selectedMeter.setLongitude(BigDecimal.valueOf(response.getLocation().getLongitude()));
            meterService.saveInstance(this.selectedMeter);
            this.enteredIP = null;
            PrimeFaces.current().executeScript("PF('meter_dialog').hide()");
            PrimeFaces.current().ajax().update("meterTable");
        } catch (Exception ex) {
            UiUtils.ComposeFailure("Action failed",ex.getLocalizedMessage());
            Logger.getLogger(MeterView.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadFilterReset();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public Meter getSelectedMeter() {
		return selectedMeter;
	}

	public void setSelectedMeter(Meter selectedMeter) {
		this.selectedMeter = selectedMeter;
	}

	public String getEnteredIP() {
		return enteredIP;
	}

	public void setEnteredIP(String enteredIP) {
		this.enteredIP = enteredIP;
	}

	public User getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(User selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public List<User> getLoadUsers() {
		return loadUsers;
	}

	public void setLoadUsers(List<User> loadUsers) {
		this.loadUsers = loadUsers;
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
