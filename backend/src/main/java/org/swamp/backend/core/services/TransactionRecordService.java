package org.swamp.backend.core.services;

import java.math.BigDecimal;

import org.sers.webutils.model.security.User;
import org.swamp.backend.models.transactionRecord.TransactionRecord;

/**
 * Responsible for all CRUD operations on {@link TransactionRecord}
 * @author Ali
 *
 */
public interface TransactionRecordService extends GenericService<TransactionRecord> {
	
	/**
	 * Responsible for counting the total money that has ever been added to the system
	 * @return
	 */
	BigDecimal getTotalMoney();
	
	/**
	 * Responsible for counting the total money the system admin has made
	 * @return
	 */
	BigDecimal getTotalAdminMoney(User user);

}
