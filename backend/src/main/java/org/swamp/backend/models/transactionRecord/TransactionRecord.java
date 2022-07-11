package org.swamp.backend.models.transactionRecord;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.swamp.backend.models.meter.Meter;

@Entity
@Table(name = "transaction_records")
public class TransactionRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private BigDecimal amountPaid = BigDecimal.ZERO;
	private BigDecimal waterVolumeCollected = BigDecimal.ZERO;
	private Meter meterId;
	
	@Column(name = "amount_paid", columnDefinition = "Decimal(6,2) default'0.00'")
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	@Column(name = "water_volume_collected", columnDefinition = "Decimal(20,2) default'0.00'")
	public BigDecimal getWaterVolumeCollected() {
		return waterVolumeCollected;
	}
	
	public void setWaterVolumeCollected(BigDecimal waterVolumeCollected) {
		this.waterVolumeCollected = waterVolumeCollected;
	}
	
	@ManyToOne
	@JoinColumn(name = "meter_id", nullable = true)
	public Meter getMeterId() {
		return meterId;
	}
	
	public void setMeterId(Meter meterId) {
		this.meterId = meterId;
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
		return obj instanceof TransactionRecord && (super.getId() != null) ? super.getId().equals(((TransactionRecord) obj).getId()) : (obj == this);
	}
	
}
