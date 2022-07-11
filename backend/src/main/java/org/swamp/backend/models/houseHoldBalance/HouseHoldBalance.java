package org.swamp.backend.models.houseHoldBalance;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.swamp.backend.models.customer.Customer;

@Entity
@Table(name = "house_hold_balances")
public class HouseHoldBalance extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Customer userId;
	private BigDecimal balance = BigDecimal.ZERO;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	public Customer getUserId() {
		return userId;
	}
	
	public void setUserId(Customer userId) {
		this.userId = userId;
	}
	
	@Column(name = "balance", columnDefinition = "Decimal(6,2) default'0.00'")
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
		return obj instanceof HouseHoldBalance && (super.getId() != null) ? super.getId().equals(((HouseHoldBalance) obj).getId()) : (obj == this);
	}
	
}