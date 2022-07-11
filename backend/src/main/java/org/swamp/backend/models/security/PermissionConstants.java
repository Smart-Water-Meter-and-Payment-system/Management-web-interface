package org.swamp.backend.models.security;

public final class PermissionConstants {
	private PermissionConstants() {

	}
	
	@SWAMPPermission(name = "SWAMP Super Administrator", description = "Has role for swamp super administrator")
	public static final String SUPER_ADMINISTRATOR = "Super Administrator";
	
	@SWAMPPermission(name = "SWAMP System Administrator", description = "Has role for swamp system administrator")
	public static final String SYSTEM_ADMINISTRATOR = "System Administrator";
	
	@SWAMPPermission(name = "SWAMP Hardware Technician", description = "Has role for swamp hardware technician")
	public static final String HARDWARE_TECHNICIAN = "Hardware Technician";
	
}
