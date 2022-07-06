/**
 * 
 */
package org.pahappa.systems.ppms.backend.core.services;

import org.pahappa.systems.ppms.backend.models.SystemSetting;
import org.sers.webutils.model.exception.ValidationFailedException;

/**
 * Responsible for CRUD operations on {@link SystemSetting}
 * 
 * @author mmc
 *
 */
public interface SystemSettingService {
	
	SystemSetting saveSystemSetting(SystemSetting settings) throws ValidationFailedException;	
	
	SystemSetting getSystemSettings();

}
