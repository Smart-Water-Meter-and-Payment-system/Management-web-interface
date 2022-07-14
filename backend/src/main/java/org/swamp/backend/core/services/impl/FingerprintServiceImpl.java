package org.swamp.backend.core.services.impl;

import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.core.services.FingerprintService;
import org.swamp.backend.models.customer.Fingerprint;

@Service
@Transactional
public class FingerprintServiceImpl extends GenericServiceImpl<Fingerprint> implements FingerprintService {

	@Override
	public Fingerprint saveInstance(Fingerprint fingerprint)
			throws ValidationFailedException, OperationFailedException {
		if(fingerprint.getFingerprint() == null)
			throw new ValidationFailedException("Missing Fingerprint");
		if(fingerprint.getUserId() == null)
			throw new ValidationFailedException("Missing Customer Details");
		return super.merge(fingerprint);
	}

	@Override
	public boolean isDeletable(Fingerprint instance) throws OperationFailedException {
		return true;
	}

}
