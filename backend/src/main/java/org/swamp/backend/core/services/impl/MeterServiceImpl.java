package org.swamp.backend.core.services.impl;

import org.apache.commons.lang.StringUtils;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.models.meter.Meter;

@Service
@Transactional
public class MeterServiceImpl extends GenericServiceImpl<Meter> implements MeterService {

	@Override
	public Meter saveInstance(Meter meter) throws ValidationFailedException, OperationFailedException {
		if(meter.getGpsCoordinates() == null)
			throw new ValidationFailedException("Missing GPS coordinates");
		if(meter.getRegionName() == null || StringUtils.isBlank(meter.getRegionName()))
			throw new ValidationFailedException("Missing Region Name");
		if(meter.getUserId() == null)
			throw new ValidationFailedException("Missing Owner's Details");
		return super.merge(meter);
	}

	@Override
	public boolean isDeletable(Meter instance) throws OperationFailedException {
		return true;
	}

}
