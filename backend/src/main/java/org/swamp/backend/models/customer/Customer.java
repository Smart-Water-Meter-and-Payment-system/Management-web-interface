package org.swamp.backend.models.customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.swamp.backend.models.houseHoldBalance.HouseHoldBalance;
import org.swamp.backend.models.meter.Meter;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private Meter meterId;
	private HouseHoldBalance householdBalance;
	
	@Column(name = "phone_number", nullable = false, columnDefinition = "varchar(10)")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "meter_id", nullable = true)
	public Meter getMeterId() {
		return meterId;
	}
	public void setMeterId(Meter meterId) {
		this.meterId = meterId;
	}
	
	@OneToOne(mappedBy = "userId")
	public HouseHoldBalance getHouseholdBalance() {
		return householdBalance;
	}
	public void setHouseholdBalance(HouseHoldBalance householdBalance) {
		this.householdBalance = householdBalance;
	}
	
	@Override
	public String toString() {
		return this.getPhoneNumber();
	}

	@Override
	public int hashCode() {
		return super.getId() != null ? this.getClass().hashCode() + super.getId().hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Customer && (super.getId() != null) ? super.getId().equals(((Customer) obj).getId()) : (obj == this);
	}
	
}
