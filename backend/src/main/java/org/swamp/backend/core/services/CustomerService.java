package org.swamp.backend.core.services;

import java.util.List;

import org.sers.webutils.model.security.User;
import org.swamp.backend.models.customer.Customer;

/**
 * Responsible for all CRUD operations on {@link Customer}
 * @author Doki
 *
 */
public interface CustomerService extends GenericService<Customer> {
	
	/**
	 * Getting the customers attached to a system admin
	 * @param user
	 * @return
	 */
	List<Customer> getSystemAdminCustomers(User user);
	
}
