package org.pahappa.systems.ppms.frontend.views;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.pahappa.systems.ppms.backend.core.services.SystemSettingService;

import org.pahappa.systems.ppms.backend.models.SystemSetting;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

@ManagedBean(name = "settingsForm")
@SessionScoped
@ViewPath(path = HyperLinks.APP_SETTINGS_FORM)
public class SystemSettingForm extends WebFormView<SystemSetting, SystemSettingForm, Dashboard> {

    private static final long serialVersionUID = 1L;
    private SystemSettingService appSettingService;
    
    @Override
    public void beanInit() {
        this.appSettingService = ApplicationContextProvider.getBean(SystemSettingService.class);
        if (this.appSettingService.getSystemSettings() != null) {
            super.model = this.appSettingService.getSystemSettings();
        } else {
            super.model = new SystemSetting();
        }
        
    }

    @Override
    public void pageLoadInit() {
        if (this.appSettingService.getSystemSettings() != null) {
            super.model = this.appSettingService.getSystemSettings();
        } else {
            super.model = new SystemSetting();
        }
    }

    @Override
    public void persist() throws Exception {
        this.appSettingService.saveSystemSetting(super.model);
        UiUtils.showMessageBox("App settings updated", "Action Successful");
    }

    @Override
    public void resetModal() {
        super.resetModal();
        super.model = new SystemSetting();
    }

    @Override
    public void setFormProperties() {
        super.setFormProperties();
    }

    @Override
    public String getViewUrl() {
        return this.getViewPath();
    }

    public SystemSettingService getAppSettingService() {
        return appSettingService;
    }

    public void setAppSettingService(SystemSettingService appSettingService) {
        this.appSettingService = appSettingService;
    }

    

}
