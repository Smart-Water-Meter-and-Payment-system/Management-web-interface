package org.swamp.backend.models.waterChargeRate;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.swamp.backend.constants.ChargeRateStatus;
import org.swamp.backend.models.meter.Meter;

/**
 * This model will be used by admins to set the water charge rate.
 * @author Collins
 *
 */
@Entity
@Table(name = "water_charge_rate")
public class WaterChargeRate extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//water volume unit which should be used to calculate how much to charge
	private BigDecimal waterVolume = BigDecimal.ZERO;
	
	//money to charge for the water volume set
	private BigDecimal charge = BigDecimal.ZERO;
	
	//status of the water charge rate, only the active one will be used for calculating the money
	private ChargeRateStatus activated = ChargeRateStatus.NO;
	
	//the meter for which this water charge rate is being attached
	private Meter meterId;
	
	@Column(name = "water_volume", columnDefinition = "Decimal(20,2) default'0.00'")
	public BigDecimal getWaterVolume() {
		return waterVolume;
	}
	
	public void setWaterVolume(BigDecimal waterVolume) {
		this.waterVolume = waterVolume;
	}
	
	@Column(name = "charge", columnDefinition = "Decimal(6,2) default'0.00'")
	public BigDecimal getCharge() {
		return charge;
	}
	
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	
	@ManyToOne
	@JoinColumn(name = "meter_id", nullable = true)
	public Meter getMeterId() {
		return meterId;
	}
	
	public void setMeterId(Meter meterId) {
		this.meterId = meterId;
	}

    @Column(name = "activated", columnDefinition = "varchar(25) default 'NO'")
    @Enumerated(value = EnumType.STRING)
	public ChargeRateStatus getActivated() {
		return activated;
	}

	public void setActivated(ChargeRateStatus activated) {
		this.activated = activated;
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
		return obj instanceof WaterChargeRate && (super.getId() != null) ? super.getId().equals(((WaterChargeRate) obj).getId()) : (obj == this);
	}
	
}
