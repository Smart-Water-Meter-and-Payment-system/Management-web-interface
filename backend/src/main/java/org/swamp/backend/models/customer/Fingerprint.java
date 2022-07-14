package org.swamp.backend.models.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sers.webutils.model.BaseEntity;

/**
 * This model will hold all fingerprints for the users that register with the system
 * @author Collins
 *
 */
@Entity
@Table(name = "fingerprints")
public class Fingerprint extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//the user's fingerprint
	private byte[] fingerprint;
	
	//the user to whom the fingerprint belongs
	private Customer userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	public Customer getUserId() {
		return userId;
	}
	
	public void setUserId(Customer userId) {
		this.userId = userId;
	}

	@Lob
    @Column(name = "fingerprint", columnDefinition="BLOB")
	public byte[] getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(byte[] fingerprint) {
		this.fingerprint = fingerprint;
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
		return obj instanceof Fingerprint && (super.getId() != null) ? super.getId().equals(((Fingerprint) obj).getId()) : (obj == this);
	}
	
}