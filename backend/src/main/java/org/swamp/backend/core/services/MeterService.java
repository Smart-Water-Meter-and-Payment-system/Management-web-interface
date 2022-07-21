package org.swamp.backend.core.services;

import java.io.IOException;

import org.swamp.backend.models.meter.Meter;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
/**
 * Responsible for all CRUD operations on {@link Meter}
 * @author Doki
 *
 */
public interface MeterService extends GenericService<Meter> {

	/**
	 * Responsible for getting the location of {@link Meter}
	 * @param IP
	 * @return
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	CityResponse getLocation(String IP) throws IOException, GeoIp2Exception;

}
