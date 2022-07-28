package org.swamp.backend.core.services.impl;

import java.util.List;

import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.CustomerService;
import org.swamp.backend.core.services.MeterService;
import org.swamp.backend.models.customer.Customer;

import com.googlecode.genericdao.search.Search;

@Service
@Transactional
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService{
	
	@Autowired
	MeterService meterService;

	@Override
	public Customer saveInstance(Customer customer) throws ValidationFailedException, OperationFailedException {
		return super.merge(customer);
	}

	@Override
	public boolean isDeletable(Customer instance) throws OperationFailedException {
		return true;
	}

	@Override
	public List<Customer> getSystemAdminCustomers(User user) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterIn("meterId", meterService.getAdminMeters(user));
		return super.getInstances(search, 0, 0);
	}

}
