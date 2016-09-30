package weibo4j.examples.oauth2;

import org.apache.log4j.Logger;

public class Log {
	
	static Logger log = Logger.getLogger(Log.class.getName());
	
    public static void logDebug(String message) {
			log.debug(message);
	}

	public static String logInfo(String message) {
			return message;
	}


}
