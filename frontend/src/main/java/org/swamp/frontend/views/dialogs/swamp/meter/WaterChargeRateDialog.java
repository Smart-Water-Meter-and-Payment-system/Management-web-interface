package org.swamp.frontend.views.dialogs.swamp.meter;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.swamp.backend.constants.ChargeRateStatus;
import org.swamp.backend.core.services.WaterChargeRateService;
import org.swamp.backend.models.meter.Meter;
import org.swamp.backend.models.waterChargeRate.WaterChargeRate;
import org.swamp.frontend.views.MessageComposer;
import org.swamp.frontend.views.dialogs.DialogForm;

import com.googlecode.genericdao.search.Search;

@ManagedBean(name = "dialogWaterChargeRate")
@SessionScoped
public class WaterChargeRateDialog extends DialogForm<WaterChargeRate> {
	
	private static final long serialVersionUID = 1L;
	private static final String DIALOG_NAME = "dialogWaterChargeRate";
	private WaterChargeRateService waterChargeRateService;
	private Meter selectedMeter;
	private List<ChargeRateStatus> chargeStatus;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);

	public WaterChargeRateDialog() {
		super(DIALOG_NAME, 500, 290);
		this.setWaterChargeRateService(ApplicationContextProvider.getBean(WaterChargeRateService.class));
		this.chargeStatus = Arrays.asList(ChargeRateStatus.values());
	}

	@Override
	public void persist() {
		try {
			if(super.model.getActivated().equals(ChargeRateStatus.YES)) {
				if(!this.waterChargeRateService.getInstances(search.
						addFilterEqual("activated", ChargeRateStatus.YES), 0, 0).isEmpty())
					throw new OperationFailedException("You cannot activate this rate because an active one already exists");
				super.model.setMeterId(this.selectedMeter);
				waterChargeRateService.saveInstance(super.model);
			}else {
				super.model.setMeterId(this.selectedMeter);
				waterChargeRateService.saveInstance(super.model);
			}
        } catch (Exception ex) {
        	MessageComposer.compose("Action Unsuccessful", ex.getMessage());
            Logger.getLogger(WaterChargeRateDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void resetModal() {
		super.resetModal();
		super.model = new WaterChargeRate();

	}

	public void setFormProperties() {
		super.setFormProperties();
	}

	public WaterChargeRateService getWaterChargeRateService() {
		return waterChargeRateService;
	}

	public void setWaterChargeRateService(WaterChargeRateService waterChargeRateService) {
		this.waterChargeRateService = waterChargeRateService;
	}

	public Meter getSelectedMeter() {
		return selectedMeter;
	}

	public void setSelectedMeter(Meter selectedMeter) {
		this.selectedMeter = selectedMeter;
	}

	public List<ChargeRateStatus> getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(List<ChargeRateStatus> chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

}
