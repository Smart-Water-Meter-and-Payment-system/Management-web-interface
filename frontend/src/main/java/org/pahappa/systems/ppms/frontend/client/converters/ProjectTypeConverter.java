package org.pahappa.systems.ppms.frontend.client.converters;
import org.pahappa.systems.ppms.backend.core.services.ProjectTypeService;
import org.pahappa.systems.ppms.backend.models.project.ProjectType;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("projectTypeConverter")
public class ProjectTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1, String arg2) {
		
		if (arg2 == null || arg2.isEmpty())
			return null;
		return (ProjectType) ApplicationContextProvider.getBean(ProjectTypeService.class).getInstanceByID(arg2);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object instanceof String)
			return null;
		return ((ProjectType) object).getId();
	}

}
