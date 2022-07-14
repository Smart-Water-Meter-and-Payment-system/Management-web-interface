package org.swamp.frontend.client.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.swamp.backend.core.services.HouseHoldBalanceService;
import org.swamp.backend.models.houseHoldBalance.HouseHoldBalance;

@FacesConverter("houseHoldBalanceConverter")
public class HouseHoldBalanceConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty())
			return null;
                
		return ApplicationContextProvider.getBean(HouseHoldBalanceService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((HouseHoldBalance) object).getId();
	}

}
