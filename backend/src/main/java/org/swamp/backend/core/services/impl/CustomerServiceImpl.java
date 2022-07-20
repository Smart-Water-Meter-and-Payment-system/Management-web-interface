package org.swamp.backend.core.services.impl;

import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.models.customer.Customer;

@Service
@Transactional
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService{

	@Override
	public Customer saveInstance(Customer customer) throws ValidationFailedException, OperationFailedException {
		return super.merge(customer);
	}

	@Override
	public boolean isDeletable(Customer instance) throws OperationFailedException {
		return true;
	}

}
