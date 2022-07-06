package org.pahappa.systems.ppms.frontend.client.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.pahappa.systems.ppms.backend.core.services.SubmoduleService;
import org.pahappa.systems.ppms.backend.models.modules.Submodule;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;


@FacesConverter("submoduleConverter")
public class SubmoduleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty())
			return null;
                
		return ApplicationContextProvider.getBean(SubmoduleService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((Submodule) object).getId();
	}
}
