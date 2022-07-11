package org.swamp.backend.core.utils;

public class HtmlEmailTemplates {
	
	public static String resetPasswordTemplate() {
		return "";
	}
	
	public static String registrationTemplate(String username, String message, String buttonText, String requestUrl) {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html data-editor-version=\"2\" class=\"sg-campaigns\" xmlns=\"http://www.w3.org/1999/xhtml\"><head>\r\n" + 
				"      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n" + 
				"      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1\">\r\n" + 
				"      <!--[if !mso]><!-->\r\n" + 
				"      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\r\n" + 
				"      <!--<![endif]-->\r\n" + 
				"      <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"      <xml>\r\n" + 
				"        <o:OfficeDocumentSettings>\r\n" + 
				"          <o:AllowPNG/>\r\n" + 
				"          <o:PixelsPerInch>96</o:PixelsPerInch>\r\n" + 
				"        </o:OfficeDocumentSettings>\r\n" + 
				"      </xml>\r\n" + 
				"      <![endif]-->\r\n" + 
				"      <!--[if (gte mso 9)|(IE)]>\r\n" + 
				"  <style type=\"text/css\">\r\n" + 
				"    body {width: 600px;margin: 0 auto;}\r\n" + 
				"    table {border-collapse: collapse;}\r\n" + 
				"    table, td {mso-table-lspace: 0pt;mso-table-rspace: 0pt;}\r\n" + 
				"    img {-ms-interpolation-mode: bicubic;}\r\n" + 
				"  </style>\r\n" + 
				"<![endif]-->\r\n" + 
				"      <style type=\"text/css\">\r\n" + 
				"    body, p, div {\r\n" + 
				"      font-family: inherit;\r\n" + 
				"      font-size: 14px;\r\n" + 
				"    }\r\n" + 
				"    body {\r\n" + 
				"      color: #000000;\r\n" + 
				"    }\r\n" + 
				"    body a {\r\n" + 
				"      color: #1188E6;\r\n" + 
				"      text-decoration: none;\r\n" + 
				"    }\r\n" + 
				"    p { margin: 0; padding: 0; }\r\n" + 
				"    table.wrapper {\r\n" + 
				"      width:100% !important;\r\n" + 
				"      table-layout: fixed;\r\n" + 
				"      -webkit-font-smoothing: antialiased;\r\n" + 
				"      -webkit-text-size-adjust: 100%;\r\n" + 
				"      -moz-text-size-adjust: 100%;\r\n" + 
				"      -ms-text-size-adjust: 100%;\r\n" + 
				"    }\r\n" + 
				"    img.max-width {\r\n" + 
				"      max-width: 100% !important;\r\n" + 
				"    }\r\n" + 
				"    .column.of-2 {\r\n" + 
				"      width: 50%;\r\n" + 
				"    }\r\n" + 
				"    .column.of-3 {\r\n" + 
				"      width: 33.333%;\r\n" + 
				"    }\r\n" + 
				"    .column.of-4 {\r\n" + 
				"      width: 25%;\r\n" + 
				"    }\r\n" + 
				"    @media screen and (max-width:480px) {\r\n" + 
				"      .preheader .rightColumnContent,\r\n" + 
				"      .footer .rightColumnContent {\r\n" + 
				"        text-align: left !important;\r\n" + 
				"      }\r\n" + 
				"      .preheader .rightColumnContent div,\r\n" + 
				"      .preheader .rightColumnContent span,\r\n" + 
				"      .footer .rightColumnContent div,\r\n" + 
				"      .footer .rightColumnContent span {\r\n" + 
				"        text-align: left !important;\r\n" + 
				"      }\r\n" + 
				"      .preheader .rightColumnContent,\r\n" + 
				"      .preheader .leftColumnContent {\r\n" + 
				"        font-size: 80% !important;\r\n" + 
				"        padding: 5px 0;\r\n" + 
				"      }\r\n" + 
				"      table.wrapper-mobile {\r\n" + 
				"        width: 100% !important;\r\n" + 
				"        table-layout: fixed;\r\n" + 
				"      }\r\n" + 
				"      img.max-width {\r\n" + 
				"        height: auto !important;\r\n" + 
				"        max-width: 100% !important;\r\n" + 
				"      }\r\n" + 
				"      a.bulletproof-button {\r\n" + 
				"        display: block !important;\r\n" + 
				"        width: auto !important;\r\n" + 
				"        font-size: 80%;\r\n" + 
				"        padding-left: 0 !important;\r\n" + 
				"        padding-right: 0 !important;\r\n" + 
				"      }\r\n" + 
				"      .columns {\r\n" + 
				"        width: 100% !important;\r\n" + 
				"      }\r\n" + 
				"      .column {\r\n" + 
				"        display: block !important;\r\n" + 
				"        width: 100% !important;\r\n" + 
				"        padding-left: 0 !important;\r\n" + 
				"        padding-right: 0 !important;\r\n" + 
				"        margin-left: 0 !important;\r\n" + 
				"        margin-right: 0 !important;\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  </style>\r\n" + 
				"      <!--user entered Head Start--><link href=\"https://fonts.googleapis.com/css?family=Fira+Sans+Condensed&display=swap\" rel=\"stylesheet\"><style>\r\n" + 
				"    body {font-family: 'Fira Sans Condensed', sans-serif;}\r\n" + 
				"</style><!--End Head user entered-->\r\n" + 
				"    </head>\r\n" + 
				"    <body>\r\n" + 
				"      <center class=\"wrapper\" data-link-color=\"#1188E6\" data-body-style=\"font-size:14px; font-family:inherit; color:#000000; background-color:#f0f0f0;\">\r\n" + 
				"        <div class=\"webkit\">\r\n" + 
				"          <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"wrapper\" bgcolor=\"#f0f0f0\">\r\n" + 
				"            <tbody><tr>\r\n" + 
				"              <td valign=\"top\" bgcolor=\"#f0f0f0\" width=\"100%\">\r\n" + 
				"                <table width=\"100%\" role=\"content-container\" class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                  <tbody><tr>\r\n" + 
				"                    <td width=\"100%\">\r\n" + 
				"                      <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
				"                        <tbody><tr>\r\n" + 
				"                          <td>\r\n" + 
				"                            <!--[if mso]>\r\n" + 
				"    <center>\r\n" + 
				"    <table><tr><td width=\"600\">\r\n" + 
				"  <![endif]-->\r\n" + 
				"                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%; max-width:600px;\" align=\"center\">\r\n" + 
				"                                      <tbody><tr>\r\n" + 
				"                                        <td role=\"modules-container\" style=\"padding:0px 0px 0px 0px; color:#000000; text-align:left;\" bgcolor=\"#FFFFFF\" width=\"100%\" align=\"left\"><table class=\"module preheader preheader-hide\" role=\"module\" data-type=\"preheader\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"display: none !important; mso-hide: all; visibility: hidden; opacity: 0; color: transparent; height: 0; width: 0;\">\r\n" + 
				"    <tbody><tr>\r\n" + 
				"      <td role=\"module-content\">\r\n" + 
				"        <p></p>\r\n" + 
				"      </td>\r\n" + 
				"    </tr>\r\n" + 
				"  </tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"080768f5-7b16-4756-a254-88cfe5138113\">\r\n" + 
				"    <tbody>\r\n" + 
				"      <tr>\r\n" + 
				"        <td style=\"padding:30px 30px 18px 30px; line-height:36px; text-align:inherit; background-color:#4d5171;\" height=\"100%\" valign=\"top\" bgcolor=\"#4d5171\" role=\"module-content\"><div><div style=\"font-family: inherit; text-align: center\"><span style=\"color: #ffffff; font-size: 48px; font-family: inherit\">Welcome, " + username + "!</span></div><div></div></div></td>\r\n" + 
				"      </tr>\r\n" + 
				"    </tbody>\r\n" + 
				"  </table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"cddc0490-36ba-4b27-8682-87881dfbcc14\">\r\n" + 
				"    <tbody>\r\n" + 
				"      <tr>\r\n" + 
				"        <td style=\"padding:18px 30px 18px 30px; line-height:22px; text-align:inherit; background-color:#4d5171;\" height=\"100%\" valign=\"top\" bgcolor=\"#4d5171\" role=\"module-content\"><div><div style=\"font-family: inherit; text-align: inherit\"><span style=\"color: #ffffff; font-size: 15px\"> " + message + " </span></div><div></div></div></td>\r\n" + 
				"      </tr>\r\n" + 
				"    </tbody>\r\n" + 
				"  </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"module\" data-role=\"module-button\" data-type=\"button\" role=\"module\" style=\"table-layout:fixed;\" width=\"100%\" data-muid=\"cd669415-360a-41a6-b4b4-ca9e149980b3\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td align=\"center\" bgcolor=\"#4d5171\" class=\"outer-td\" style=\"padding:10px 0px 40px 0px;\">\r\n" + 
				"            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"wrapper-mobile\" style=\"text-align:center;\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                <td align=\"center\" bgcolor=\"#ffc94c\" class=\"inner-td\" style=\"border-radius:6px; font-size:16px; text-align:center; background-color:inherit;\">\r\n" + 
				"                  <a href=\"" + requestUrl + "\" style=\"background-color:#ffc94c; border:1px solid #ffc94c; border-color:#ffc94c; border-radius:40px; border-width:1px; color:#3f4259; display:inline-block; font-size:15px; font-weight:normal; letter-spacing:0px; line-height:25px; padding:12px 18px 10px 18px; text-align:center; text-decoration:none; border-style:solid; font-family:inherit; width:168px;\" target=\"_blank\">" + buttonText + "</a>\r\n" + 
				"                </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table><table class=\"module\" role=\"module\" data-type=\"divider\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"c5a3c312-4d9d-44ff-9fce-6a8003caeeca\">\r\n" + 
				"    <tbody>\r\n" + 
				"      <tr>\r\n" + 
				"        <td style=\"padding:0px 20px 0px 20px;\" role=\"module-content\" height=\"100%\" valign=\"top\" bgcolor=\"#4d5171\">\r\n" + 
				"          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" height=\"1px\" style=\"line-height:1px; font-size:1px;\">\r\n" + 
				"            <tbody>\r\n" + 
				"              <tr>\r\n" + 
				"                <td style=\"padding:0px 0px 1px 0px;\" bgcolor=\"#ffc94c\"></td>\r\n" + 
				"              </tr>\r\n" + 
				"            </tbody>\r\n" + 
				"          </table>\r\n" + 
				"        </td>\r\n" + 
				"      </tr>\r\n" + 
				"    </tbody>\r\n" + 
				"  </table>\r\n" + 
				" \r\n" + 
				"	<div data-role=\"module-unsubscribe\" class=\"module\" role=\"module\" data-type=\"unsubscribe\" style=\"color:#444444; font-size:12px; line-height:20px; padding:16px 16px 16px 16px; text-align:Center;\" data-muid=\"4e838cf3-9892-4a6d-94d6-170e474d21e5\">\r\n" + 
				"                                            <div class=\"Unsubscribe--addressLine\"><p class=\"Unsubscribe--senderName\" style=\"font-size:12px; line-height:20px;\">{{Sender_Name}}</p><p style=\"font-size:12px; line-height:20px;\"><span class=\"Unsubscribe--senderAddress\">{{Sender_Address}}</span>, <span class=\"Unsubscribe--senderCity\">{{Sender_City}}</span>, <span class=\"Unsubscribe--senderState\">{{Sender_State}}</span> <span class=\"Unsubscribe--senderZip\">{{Sender_Zip}}</span></p></div>\r\n" + 
				"                                            <p style=\"font-size:12px; line-height:20px;\"><a class=\"Unsubscribe--unsubscribeLink\" href=\"{{{unsubscribe}}}\" target=\"_blank\" style=\"\">Unsubscribe</a> - <a href=\"{{{unsubscribe_preferences}}}\" target=\"_blank\" class=\"Unsubscribe--unsubscribePreferences\" style=\"\">Unsubscribe Preferences</a></p>\r\n" + 
				"                                          </div><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"module\" data-role=\"module-button\" data-type=\"button\" role=\"module\" style=\"table-layout:fixed;\" width=\"100%\" data-muid=\"5f89d789-2bfd-48e2-a219-1de42476c398\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td align=\"center\" bgcolor=\"\" class=\"outer-td\" style=\"padding:0px 0px 20px 0px;\">\r\n" + 
				"            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"wrapper-mobile\" style=\"text-align:center;\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                <td align=\"center\" bgcolor=\"#f5f8fd\" class=\"inner-td\" style=\"border-radius:6px; font-size:16px; text-align:center; background-color:inherit;\"><a href=\"https://pahappa.com/\" style=\"background-color:#f5f8fd; border:1px solid #f5f8fd; border-color:#f5f8fd; border-radius:25px; border-width:1px; color:#a8b9d5; display:inline-block; font-size:10px; font-weight:normal; letter-spacing:0px; line-height:normal; padding:5px 18px 5px 18px; text-align:center; text-decoration:none; border-style:solid; font-family:helvetica,sans-serif;\" target=\"_blank\"> POWERED BY PAHAPPA</a></td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table></td>\r\n" + 
				"                                      </tr>\r\n" + 
				"                                    </tbody></table>\r\n" + 
				"                                    <!--[if mso]>\r\n" + 
				"                                  </td>\r\n" + 
				"                                </tr>\r\n" + 
				"                              </table>\r\n" + 
				"                            </center>\r\n" + 
				"                            <![endif]-->\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody></table>\r\n" + 
				"                    </td>\r\n" + 
				"                  </tr>\r\n" + 
				"                </tbody></table>\r\n" + 
				"              </td>\r\n" + 
				"            </tr>\r\n" + 
				"          </tbody></table>\r\n" + 
				"        </div>\r\n" + 
				"      </center>\r\n" + 
				"    \r\n" + 
				"  \r\n" + 
				"</body></html>";
		
	}

}
