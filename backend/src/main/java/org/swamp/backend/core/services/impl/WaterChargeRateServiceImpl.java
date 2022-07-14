package org.swamp.backend.core.services.impl;

import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.WaterChargeRateService;
import org.swamp.backend.models.waterChargeRate.WaterChargeRate;

@Service
@Transactional
public class WaterChargeRateServiceImpl extends GenericServiceImpl<WaterChargeRate> implements WaterChargeRateService {

	@Override
	public WaterChargeRate saveInstance(WaterChargeRate waterChargeRate)
			throws ValidationFailedException, OperationFailedException {
		if(waterChargeRate.getCharge() == null)
			throw new ValidationFailedException("Missing Water Charge Rate");
		if(waterChargeRate.getWaterVolume() == null)
			throw new ValidationFailedException("Missing Water Volume");
		if(waterChargeRate.getMeterId() == null)
			throw new ValidationFailedException("Missing Meter Details");
		return super.merge(waterChargeRate);
	}

	@Override
	public boolean isDeletable(WaterChargeRate instance) throws OperationFailedException {
		return true;
	}

}
