package org.swamp.backend.models.security;

public final class PermissionConstants {
	private PermissionConstants() {

	}
	
	@SWAMPPermission(name = "Super Administrator", description = "Has role for swamp super administrator")
	public static final String SUPER_ADMINISTRATOR = "Super Administrator";
	
	@SWAMPPermission(name = "System Administrator", description = "Has role for swamp system administrator")
	public static final String SYSTEM_ADMINISTRATOR = "System Administrator";
	
	@SWAMPPermission(name = "Hardware Technician", description = "Has role for swamp hardware technician")
	public static final String HARDWARE_TECHNICIAN = "Hardware Technician";
	
}
