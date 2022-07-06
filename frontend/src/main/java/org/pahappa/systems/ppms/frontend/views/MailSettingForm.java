package org.pahappa.systems.ppms.frontend.views;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.MailSettingService;
import org.pahappa.systems.ppms.backend.models.MailSetting;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

@ManagedBean(name = "mailSettingForm")
@SessionScoped
@ViewPath(path = HyperLinks.MAIL_SETTING_FORM)
public class MailSettingForm extends WebFormView<MailSetting, MailSettingForm, Dashboard> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MailSettingService mailSettingService;

	@Override
	public void beanInit() {
		this.mailSettingService = ApplicationContextProvider.getBean(MailSettingService.class);
	}

	@Override
	public void pageLoadInit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void persist() throws Exception {
		this.mailSettingService.saveMailSetting(super.model);
		UiUtils.showMessageBox("Mail settings updated", "Action Successful");
	}

    @Override
	public void resetModal() {
		super.resetModal();
		super.model = new MailSetting();
	}

    @Override
	public void setFormProperties() {
		super.setFormProperties();
	}

	@Override
	public String getViewUrl() {
		return this.getViewPath();
	}
}
