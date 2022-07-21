package org.swamp.frontend.client.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.swamp.backend.core.services.WaterChargeRateService;
import org.swamp.backend.models.waterChargeRate.WaterChargeRate;

@FacesConverter("waterChargeRateConverter")
public class WaterChargeRateConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty())
			return null;
                
		return ApplicationContextProvider.getBean(WaterChargeRateService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((WaterChargeRate) object).getId();
	}

}
