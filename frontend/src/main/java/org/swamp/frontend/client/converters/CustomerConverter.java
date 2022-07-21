package org.swamp.frontend.client.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.models.customer.Customer;

@FacesConverter("customerConverter")
public class CustomerConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty())
			return null;
                
		return ApplicationContextProvider.getBean(CustomerService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((Customer) object).getId();
	}

}
