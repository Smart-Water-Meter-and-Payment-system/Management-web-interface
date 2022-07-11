package org.swamp.backend.models.meter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.sers.webutils.model.security.User;

import com.itextpdf.awt.geom.Point;

@Entity
@Table(name = "meters")
public class Meter extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Point gpsCoordinates;
	private String regionName;
	private User userId;
	
	@Column(name = "gps_coordinates", nullable = true)
	public Point getGpsCoordinates() {
		return gpsCoordinates;
	}
	
	public void setGpsCoordinates(Point gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}
	
	@Column(name = "region_name", nullable = false, columnDefinition = "varchar(20)")
	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	public User getUserId() {
		return userId;
	}
	
	public void setUserId(User userId) {
		this.userId = userId;
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
