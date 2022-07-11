package org.swamp.backend.core.services;

import org.sers.webutils.model.exception.ValidationFailedException;
import org.swamp.backend.models.MailSetting;

/**
 * Responsible for CRUD operations on {@link MailSetting}
 * 
 * @author mmc
 *
 */
public interface MailSettingService {
	/**
	 * Adds a {@link MailSetting} to the database.
	 * 
	 * @param mailSetting
	 * @throws ValidationFailedException if the following attributes are blank:
	 *               senderAddress, senderPassword, senderSmtpHost, senderSmtpPort
	 */
	MailSetting saveMailSetting(MailSetting mailSetting) throws ValidationFailedException;

	/**
	 * Gets mail settings
	 * 
	 * @return
	 */
	MailSetting getMailSetting();
}
