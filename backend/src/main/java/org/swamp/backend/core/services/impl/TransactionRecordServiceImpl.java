package org.swamp.backend.core.services.impl;

import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.TransactionRecordService;
import org.swamp.backend.models.transactionRecord.TransactionRecord;

@Service
@Transactional
public class TransactionRecordServiceImpl extends GenericServiceImpl<TransactionRecord> implements TransactionRecordService {

	@Override
	public TransactionRecord saveInstance(TransactionRecord transactionRecord)
			throws ValidationFailedException, OperationFailedException {
		if(transactionRecord.getAmountPaid() ==  null)
			throw new ValidationFailedException("Missing Amount Paid");
		if(transactionRecord.getWaterVolumeCollected() == null)
			throw new ValidationFailedException("Missing Water Volume Collected");
		if(transactionRecord.getMeterId() == null)
			throw new ValidationFailedException("Missing Meter Details");
		return super.merge(transactionRecord);
	}

	@Override
	public boolean isDeletable(TransactionRecord instance) throws OperationFailedException {
		return true;
	}

}
