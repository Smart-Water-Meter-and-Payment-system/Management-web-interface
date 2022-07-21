package org.swamp.backend.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class GeoLocation {

	public static void main(String... args) throws IOException, GeoIp2Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));
	    String ip = in.readLine();
	    String dbLocation = "/home/ttc/Downloads/Compressed/GeoLite2-City_20220712/GeoLite2-City.mmdb";
	        
	    File database = new File(dbLocation);
	    DatabaseReader dbReader = new DatabaseReader.Builder(database)
	      .build();
	        
	    InetAddress ipAddress = InetAddress.getByName(ip);
	    CityResponse response = dbReader.city(ipAddress);
	        
	    String countryName = response.getCountry().getName();
	    String cityName = response.getCity().getName();
	    String postal = response.getPostal().getCode();
	    String state = response.getLeastSpecificSubdivision().getName();
	    System.out.println("IP: "+ip+"====="+ipAddress);
	    System.out.println(BigDecimal.valueOf(response.getLocation().getLatitude()));
	    System.out.println("Country: "+countryName);
	    System.out.println("City: "+cityName);
	    System.out.println(response.getLocation());
	}
	
}
