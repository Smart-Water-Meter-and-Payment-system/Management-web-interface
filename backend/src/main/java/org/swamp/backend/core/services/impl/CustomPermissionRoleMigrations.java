package org.swamp.backend.core.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.migrations.Migration;
import org.sers.webutils.model.security.Permission;
import org.sers.webutils.model.security.Role;
import org.sers.webutils.server.core.dao.PermissionDao;
import org.sers.webutils.server.core.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swamp.backend.models.security.SWAMPPermissionInterpreter;

@Service
@Transactional
public class CustomPermissionRoleMigrations {

	@Autowired
	PermissionDao permissionDao;

	@Autowired
	RoleDao roleDao;

	@Migration(orderNumber = 1)
	public void savePermissions() {

		//Migrating Permissions
		for (Permission permission : SWAMPPermissionInterpreter.reflectivelyGetPermissions()) {
			if (permissionDao.searchUniqueByPropertyEqual("name", permission.getName()) == null) {

				try {
					permission.setRecordStatus(RecordStatus.ACTIVE);
					permission.setDateCreated(new Date());
					permission.setDateChanged(new Date());
					Permission saved = permissionDao.mergeBG(permission);

					Role role = new Role();
					role.setRecordStatus(RecordStatus.ACTIVE);
					role.setDescription(permission.getDescription());
					role.setName(permission.getName());
					role.setPermissions(new HashSet<>(Arrays.asList(new Permission[] { saved })));
					roleDao.mergeBG(role);
				} catch (Exception exe) {
					System.out.println("Permission already exists");
				}
			}
		}
		
	}
}
