package org.pahappa.systems.ppms.backend.core.services.impl;

import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.pahappa.systems.ppms.backend.core.services.SystemSettingService;
import org.pahappa.systems.ppms.backend.models.SystemSetting;

@Service
@Transactional
public class SystemSettingServiceImpl extends BaseDAOImpl<SystemSetting> implements SystemSettingService {


    @Override
    public SystemSetting saveSystemSetting(SystemSetting settings) throws ValidationFailedException {
        return  super.save(settings);
    }

    @Override
    public SystemSetting getSystemSettings() {
       
        if(super.findAll()==null||super.findAll().isEmpty()){
        	return null;
        }
        return super.findAll().get(0);
    }

   
}
