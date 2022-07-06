package org.pahappa.systems.ppms.backend.models.security;

public final class PermissionConstants {
	private PermissionConstants() {

	}
	
	@PPMSPermission(name = "Api user", description = "Has role for api users")
	public static final String PERM_API_USER = "Api User";
	
	@PPMSPermission(name = "PPMS Developer", description = "Has role for pahappa developer")
	public static final String PERM_PPMS_DEV = "Pahappa Developer";
	
	@PPMSPermission(name = "PPMS SalesPerson", description = "Has role for pahappa salesperson")
	public static final String PERM_PPMS_SALESPERSON = "Pahappa Salesperson";
	
	@PPMSPermission(name = "PPMS TechnicalManager", description = "Has role for pahappa technical manager")
	public static final String PERM_PPMS_TECHMANAGER = "Pahappa Technical Manager";
	
	@PPMSPermission(name = "PPMS ProjectLead", description = "Has role for pahappa project lead")
	public static final String PERM_PPMS_PROJECTLEAD = "Pahappa Project Lead";
	
	
}
