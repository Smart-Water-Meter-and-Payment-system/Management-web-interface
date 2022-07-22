package org.swamp.backend.core.services;

import java.util.List;

import org.swamp.backend.models.meter.Meter;
import org.swamp.backend.models.waterChargeRate.WaterChargeRate;

/**
 * Responsible for all CRUD operations on {@link WaterChargeRate}
 * @author Eric
 *
 */
public interface WaterChargeRateService extends GenericService<WaterChargeRate> {
	
	/**
	 * Returns a rate from the DB that is activated
	 * @return
	 */
	List<WaterChargeRate> getActivatedRate(Meter meter);

}
