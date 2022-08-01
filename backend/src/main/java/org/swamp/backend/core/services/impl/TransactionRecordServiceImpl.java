package org.swamp.backend.core.services.impl;

import java.math.BigDecimal;

import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.core.services.TransactionRecordService;
import org.swamp.backend.models.transactionRecord.TransactionRecord;

import com.googlecode.genericdao.search.Search;

@Service
@Transactional
public class TransactionRecordServiceImpl extends GenericServiceImpl<TransactionRecord> implements TransactionRecordService {

	@Autowired
	MeterService meterService;
	
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

	@Override
	public BigDecimal getTotalMoney() {
		Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		BigDecimal totalMoney = BigDecimal.ZERO;
		for(TransactionRecord transactionRecord : super.getInstances(search, 0, 0)) {
			totalMoney.add(transactionRecord.getAmountPaid());
		}		
		return totalMoney;
	}

	@Override
	public BigDecimal getTotalAdminMoney(User user) {
		Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterIn("meterId", meterService.getAdminMeters(user));
		BigDecimal totalMoney = BigDecimal.ZERO;
		for(TransactionRecord transactionRecord : super.getInstances(search, 0, 0)) {
			totalMoney.add(transactionRecord.getAmountPaid());
		}		
		return totalMoney;
	}

}
