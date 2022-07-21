package org.swamp.backend.models.houseHoldBalance;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;
import org.swamp.backend.models.customer.Customer;

/**
 * This model will store all the balance that is left by the users so that they can reuse it
 * @author Collins
 *
 */
@Entity
@Table(name = "house_hold_balances")
public class HouseHoldBalance extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//the user who owns the balance
	private Customer userId;
	
	//the total balance that was left
	private BigDecimal balance = BigDecimal.ZERO;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", nullable = true, referencedColumnName = "id")
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
