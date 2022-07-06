/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pahappa.systems.ppms.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.sers.webutils.model.BaseEntity;

/**
 *
 * @author Ray Gdhrt
 */
@Entity
@Table(name = "system_settings")
public class SystemSetting extends BaseEntity{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String systemName;
    private String systemServiceCode;
    

   

    @Column(name = "system_name")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }


     @Column(name = "service_code",length = 10)
    public String getSystemServiceCode() {
        return systemServiceCode;
    }

    public void setSystemServiceCode(String systemServiceCode) {
        this.systemServiceCode = systemServiceCode;
    }
    
    

}
