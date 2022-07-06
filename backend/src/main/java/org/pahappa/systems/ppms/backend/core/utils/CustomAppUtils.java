/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pahappa.systems.ppms.backend.core.utils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Formats a valid phone number to format 256xxxxxxx returns null if invalid
 * number is supplied
 */
public class CustomAppUtils {

    public static final int MAX_CONTENT_PACKAGES = 9;
    public static final int MAX_MESSAGES_PER_BATCH = 100;
    public static final int EGO_SMS_MESSAGE_COST = 30;
    public static final int MIN_TRANSACRION_AMOUNT = 1000;
     public static final String SECTORS_LOOK_UP_NAME = "Sectors";
    /**
     * Cloudinary credentials
     */
    public static String CLOUDINARY_CLOUD_NAME = "pahappa-limited";
    public static String CLOUDINARY_API_KEY = "868549259428728";
    public static String CLOUDINARY_API_SECRET = "OabeWeOeffd-VJ4-KNSUdYXKyoo";
    public static String CLOUDINARY_FULL_URL = "cloudinary://" + CustomAppUtils.CLOUDINARY_API_KEY + ":" + CustomAppUtils.CLOUDINARY_API_SECRET + "@" + CustomAppUtils.CLOUDINARY_CLOUD_NAME;
    
    /**
     * Formats valid ugandan a phone number (To format 256xxxxxxxx) else returns
     * null
     *
     * @param phoneNumber
     * @return
     */
    public static String validateUgandanPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        if (phoneNumber.matches("\\d{12}") && phoneNumber.startsWith("256")) {
            return phoneNumber;
        } else if (phoneNumber.matches("\\d{9}") && phoneNumber.startsWith("7")) {
            return "256" + phoneNumber;
        } else if (phoneNumber.matches("\\d{10}") && phoneNumber.startsWith("07")) {
            return "256" + phoneNumber.substring(1);
        } else {
            return null;
        }
    }

    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }

    public static String getSysteUptime() {
        Date date = new Date(ManagementFactory.getRuntimeMXBean().getUptime());
        DateFormat formatter = new SimpleDateFormat("HH'hr':mm'min':ss'secs'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }
    
    public static void main(String[] args) throws IOException {
       // uploadImageToCloudinary(null, "none");
       int number =40;
       String padded = String.format("%03d" , number);
        System.out.println("Padded number is " + padded);
      

    }

}
