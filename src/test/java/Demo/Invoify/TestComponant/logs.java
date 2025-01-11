package Demo.Invoify.TestComponant;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.logging.Logs;

import com.aventstack.extentreports.Status;


public class logs {
	
	private final static Logger LOCAL_LOGGER = LogManager.getLogger(Logs.class);
	
	public static void logInfoMessage(String message, Boolean includeInReport, Status status) {
		LOCAL_LOGGER.info(message);
	}
	
	public static void logErrorMessage(String message, Boolean includeInReport, Status status) {
		LOCAL_LOGGER.error(message);
	}
	
	public static void logWarningMessage(String message, Boolean includeInReport, Status status) {
		LOCAL_LOGGER.warn(message);
	}
	
	public static void logDebugMessage(String message, Boolean includeInReport, Status status) {
		LOCAL_LOGGER.debug(message);
	}
}
