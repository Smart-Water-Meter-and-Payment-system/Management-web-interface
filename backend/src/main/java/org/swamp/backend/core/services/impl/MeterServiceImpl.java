package org.swamp.backend.core.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.models.meter.Meter;

import com.googlecode.genericdao.search.Search;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

@Service
@Transactional
public class MeterServiceImpl extends GenericServiceImpl<Meter> implements MeterService{

	@Override
	public Meter saveInstance(Meter meter) throws ValidationFailedException, OperationFailedException {
		if(meter.getCountryName() == null || StringUtils.isBlank(meter.getCountryName()))
			throw new ValidationFailedException("Missing Country Name");
		if(meter.getCityName() == null || StringUtils.isBlank(meter.getCityName()))
			throw new ValidationFailedException("Missing City Name");
		if(meter.getLongitude() == null)
			throw new ValidationFailedException("Missing Longitude");
		if(meter.getLatitude() == null)
			throw new ValidationFailedException("Missing Latitude");
		if(meter.getUserId() == null)
			throw new ValidationFailedException("Missing Owner's Details");
		return super.merge(meter);
	}
	
	@Override
	public CityResponse getLocation(String IP) throws IOException, GeoIp2Exception {
		String dbLocation = "/home/ttc/Downloads/Compressed/GeoLite2-City_20220712/GeoLite2-City.mmdb";

		File database = new File(dbLocation);
		DatabaseReader dbReader = new DatabaseReader.Builder(database)
				.build();

		InetAddress ipAddress = InetAddress.getByName(IP);
		CityResponse response = dbReader.city(ipAddress);
		return response;
	}

	@Override
	public boolean isDeletable(Meter instance) throws OperationFailedException {
		return true;
	}

	@Override
	public List<Meter> getAdminMeters(User user) {
		Search search = new Search();	
		return super.getInstances(search.addFilterEqual("userId", user), 0, 0);
	}

}
