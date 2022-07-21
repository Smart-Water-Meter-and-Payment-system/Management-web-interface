package org.swamp.backend.models.meter;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.sers.webutils.model.security.User;

/**
 * This model will store details of meters that have been bought and installed
 * @author Collins
 *
 */
@Entity
@Table(name = "meters")
public class Meter extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//country where meter is installed
	private String countryName;
	
	//city where the meter is installed
	private String cityName;
	
	//longitude of the location of the meter
	private BigDecimal longitude = BigDecimal.ZERO;
	
	//latitude of the location of the meter
	private BigDecimal latitude = BigDecimal.ZERO;
	
	//the owner of the meter
	private User userId;
	
	//the public IP for the meter
	private String publicIp;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	public User getUserId() {
		return userId;
	}
	
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	@Column(name = "country_name", nullable = false, columnDefinition = "varchar(50)")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "city_name", nullable = false, columnDefinition = "varchar(50)")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "longitude", columnDefinition = "Decimal(6,2) default'0.00'")
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", columnDefinition = "Decimal(6,2) default'0.00'")
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	@Column(name = "public_ip")
	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	@Override
	public String toString() {
		return this.getId();
	}

	@Override
	public int hashCode() {
		return super.getId() != null ? this.getClass().hashCode() + super.getId().hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Meter && (super.getId() != null) ? super.getId().equals(((Meter) obj).getId()) : (obj == this);
	}

}
