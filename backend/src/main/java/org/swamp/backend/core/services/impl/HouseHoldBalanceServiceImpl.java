package org.swamp.backend.core.services.impl;

import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.HouseHoldBalanceService;
import org.swamp.backend.models.houseHoldBalance.HouseHoldBalance;

@Service
@Transactional
public class HouseHoldBalanceServiceImpl extends GenericServiceImpl<HouseHoldBalance> implements HouseHoldBalanceService {

	@Override
	public HouseHoldBalance saveInstance(HouseHoldBalance houseHoldBalance)
			throws ValidationFailedException, OperationFailedException {
		if(houseHoldBalance.getUserId() == null)
			throw new ValidationFailedException("Missing Customer Details");
		if(houseHoldBalance.getBalance() == null)
			throw new ValidationFailedException("Missing Balance");
		return super.merge(houseHoldBalance);
	}

	@Override
	public boolean isDeletable(HouseHoldBalance instance) throws OperationFailedException {
		return true;
	}

}
