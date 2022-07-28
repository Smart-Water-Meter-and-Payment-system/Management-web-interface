package org.swamp.frontend.views.swamp.meter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.sers.webutils.client.views.presenters.PaginatedTable;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.swamp.backend.constants.ChargeRateStatus;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.core.services.TransactionRecordService;
import org.swamp.backend.core.services.WaterChargeRateService;
import org.swamp.backend.core.utils.CustomSearchUtils;
import org.swamp.backend.models.customer.Customer;
import org.swamp.backend.models.meter.Meter;
import org.swamp.backend.models.transactionRecord.TransactionRecord;
import org.swamp.backend.models.waterChargeRate.WaterChargeRate;
import org.swamp.frontend.security.HyperLinks;
import org.swamp.frontend.views.MessageComposer;

import com.googlecode.genericdao.search.Search;

@ManagedBean(name = "meterAccountView")
@SessionScoped
@ViewPath(path = HyperLinks.METER_ACCOUNT_VIEW)
public class MeterAccountView extends WebFormView<Meter, MeterAccountView, MeterView> {

	private static final long serialVersionUID = 1L;

	private transient PaginatedTable<WaterChargeRate> waterChargeRateTable;
	private transient PaginatedTable<Customer> customersTable;
	private transient PaginatedTable<TransactionRecord> transactionRecordsTable;

	private transient WaterChargeRateService waterChargeRateService;
	private transient CustomerService customerService;
	private transient TransactionRecordService transactionRecordService;

	private SortField sortField = new SortField("dateCreated", "dateCreated", true);
	private SortField selectedSortField;
	private List<Meter> meters, selectedMeters = new ArrayList<Meter>();
	private List<ChargeRateStatus> chargeRateStatuses, selectedChargeRateStatus = new ArrayList<ChargeRateStatus>();
	private String customerSearchTerm, waterChargeRateSearchTerm, transactionRecordSearchTerm;
	private BigDecimal charge, waterVolume;
	private Meter meter;

	@Override
	public void beanInit() {
		waterChargeRateService = ApplicationContextProvider.getBean(WaterChargeRateService.class);
		customerService = ApplicationContextProvider.getBean(CustomerService.class);
		transactionRecordService = ApplicationContextProvider.getBean(TransactionRecordService.class);
		meters = ApplicationContextProvider.getBean(MeterService.class).getAllInstances();
		chargeRateStatuses = Arrays.asList(ChargeRateStatus.values());
		
		resetModal();
		pageLoadInit();
	}

	@Override
	public void pageLoadInit() {
		reloadCustomersView();
		reloadWaterChargeRateView();
		reloadTransactionRecordView();
		getActivatedRate();
	}

	public void reloadCustomersView() {
		this.customersTable = new PaginatedTable<Customer>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reloadFromDB(int offset, int limit, Map<String, Object> arg2) throws Exception {
				if (MeterAccountView.this.model == null)
					return;

				Search search = CustomSearchUtils.generateSearchObjectForCustomers(customerSearchTerm, selectedSortField, 
						selectedMeters);
				search.addFilterEqual("meterId", MeterAccountView.this.model);
				
				super.setTotalRecords(customerService.countInstances(search));
				super.setDataModels(customerService.getInstances(search, offset, limit));
			}

			@Override
			public String getFileName() {
				return null;
			}

			@Override
			public List<ExcelReport> getExcelReportModels() {
				return Collections.emptyList();
			}
		};
	}
	
	public void reloadWaterChargeRateView() {
		this.waterChargeRateTable = new PaginatedTable<WaterChargeRate>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reloadFromDB(int offset, int limit, Map<String, Object> arg2) throws Exception {
				if (MeterAccountView.this.model == null)
					return;

				Search search = CustomSearchUtils.generateSearchObjectForWaterChargeRate(waterChargeRateSearchTerm, 
						selectedSortField, selectedChargeRateStatus);
				search.addFilterEqual("meterId", MeterAccountView.this.model);
				
				super.setTotalRecords(waterChargeRateService.countInstances(search));
				super.setDataModels(waterChargeRateService.getInstances(search, offset, limit));
			}

			@Override
			public String getFileName() {
				return null;
			}

			@Override
			public List<ExcelReport> getExcelReportModels() {
				return Collections.emptyList();
			}
		};
	}
	
	public void reloadTransactionRecordView() {
		this.transactionRecordsTable = new PaginatedTable<TransactionRecord>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reloadFromDB(int offset, int limit, Map<String, Object> arg2) throws Exception {
				if (MeterAccountView.this.model == null)
					return;

				Search search = CustomSearchUtils.generateSearchObjectForTransactionRecord(transactionRecordSearchTerm, 
						selectedSortField);
				search.addFilterEqual("meterId", MeterAccountView.this.model);
				
				super.setTotalRecords(transactionRecordService.countInstances(search));
				super.setDataModels(transactionRecordService.getInstances(search, offset, limit));
			}

			@Override
			public String getFileName() {
				return null;
			}

			@Override
			public List<ExcelReport> getExcelReportModels() {
				return Collections.emptyList();
			}
		};
	}

	public void removeWaterChargeRate(WaterChargeRate waterChargeRate) {
		try {
			if(waterChargeRate.getActivated().equals(ChargeRateStatus.NO)) {
				this.waterChargeRateService.deleteInstance(waterChargeRate);
				reloadWaterChargeRateView();
				MessageComposer.compose("Action Successful", "Rate has been deleted");
			} else {
				throw new OperationFailedException("This Rate cannot be deleted. It is Active");
			}
		} catch (Exception e) {
			MessageComposer.compose("Action Unsuccessful", e.getMessage());
		}
	}
	
	public void getActivatedRate() {
		List<WaterChargeRate> activeRate = this.waterChargeRateService.getActivatedRate(this.meter);
		if(!activeRate.isEmpty()) {
			for(WaterChargeRate chargeRate: this.waterChargeRateService.getActivatedRate(this.meter)) {
				this.charge = chargeRate.getCharge();
				this.waterVolume = chargeRate.getWaterVolume();
			}
			System.out.println(this.charge+"==============="+this.waterVolume);
		}else {
			this.charge = null;
			this.waterVolume = null;
			System.out.println(this.charge+"==============="+this.waterVolume);
		}
	}

	@Override
	public void persist() throws Exception {
	}

	@Override
	public void resetModal() {
		super.resetModal();
		super.model = new Meter();
	}

	@Override
	public String getViewUrl() {
		return HyperLinks.METER_ACCOUNT_VIEW;
	}

	public PaginatedTable<WaterChargeRate> getWaterChargeRateTable() {
		return waterChargeRateTable;
	}

	public void setWaterChargeRateTable(PaginatedTable<WaterChargeRate> waterChargeRateTable) {
		this.waterChargeRateTable = waterChargeRateTable;
	}

	public PaginatedTable<Customer> getCustomersTable() {
		return customersTable;
	}

	public void setCustomersTable(PaginatedTable<Customer> customersTable) {
		this.customersTable = customersTable;
	}

	public PaginatedTable<TransactionRecord> getTransactionRecordsTable() {
		return transactionRecordsTable;
	}

	public void setTransactionRecordsTable(PaginatedTable<TransactionRecord> transactionRecordsTable) {
		this.transactionRecordsTable = transactionRecordsTable;
	}

	public WaterChargeRateService getWaterChargeRateService() {
		return waterChargeRateService;
	}

	public void setWaterChargeRateService(WaterChargeRateService waterChargeRateService) {
		this.waterChargeRateService = waterChargeRateService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public TransactionRecordService getTransactionRecordService() {
		return transactionRecordService;
	}

	public void setTransactionRecordService(TransactionRecordService transactionRecordService) {
		this.transactionRecordService = transactionRecordService;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
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

	public String getWaterChargeRateSearchTerm() {
		return waterChargeRateSearchTerm;
	}

	public void setWaterChargeRateSearchTerm(String waterChargeRateSearchTerm) {
		this.waterChargeRateSearchTerm = waterChargeRateSearchTerm;
	}

	public String getTransactionRecordSearchTerm() {
		return transactionRecordSearchTerm;
	}

	public void setTransactionRecordSearchTerm(String transactionRecordSearchTerm) {
		this.transactionRecordSearchTerm = transactionRecordSearchTerm;
	}

	public String getCustomerSearchTerm() {
		return customerSearchTerm;
	}

	public void setCustomerSearchTerm(String customerSearchTerm) {
		this.customerSearchTerm = customerSearchTerm;
	}

	public List<ChargeRateStatus> getChargeRateStatuses() {
		return chargeRateStatuses;
	}

	public void setChargeRateStatuses(List<ChargeRateStatus> chargeRateStatuses) {
		this.chargeRateStatuses = chargeRateStatuses;
	}

	public List<ChargeRateStatus> getSelectedChargeRateStatus() {
		return selectedChargeRateStatus;
	}

	public void setSelectedChargeRateStatus(List<ChargeRateStatus> selectedChargeRateStatus) {
		this.selectedChargeRateStatus = selectedChargeRateStatus;
	}

	public BigDecimal getWaterVolume() {
		return waterVolume;
	}

	public void setWaterVolume(BigDecimal waterVolume) {
		this.waterVolume = waterVolume;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public Meter getMeter() {
		return meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
	}
	
}
