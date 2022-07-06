package org.pahappa.systems.ppms.backend.models.security;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.sers.webutils.model.security.Permission;

public class PPMSPermissionInterpreter {

	public static final List<Permission> reflectivelyGetPermissions() {

		List<Permission> permissions = new ArrayList<Permission>();

		for (Field field : PermissionConstants.class.getFields()) {

			if (field.isAnnotationPresent(PPMSPermission.class)) {
				PPMSPermission permissionAnnotation = field.getAnnotation(PPMSPermission.class);
				Permission permission = new Permission();
				permission.setName(permissionAnnotation.name());
				permission.setDescription(permissionAnnotation.description());
				permissions.add(permission);
			}
		}
		return permissions;
	}
}
