package org.pahappa.systems.ppms.frontend.client.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;


@FacesConverter("projectConverter")
public class ProjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty())
			return null;
                
		return ApplicationContextProvider.getBean(ProjectService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((Project) object).getId();
	}
}
