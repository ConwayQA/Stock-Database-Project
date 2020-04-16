package persistance.domain;

import org.apache.log4j.Logger;

import utilities.InputScanner;

public enum Domain {

	CUSTOMER("Information on store customers"),
	ITEMS("Details on items in stock"),
	ORDERS("Collection of customers purchases of items"),
	USERS("Information on staff users of the system"),
	END("Closes the application once you are done");

	public static final Logger LOGGER = Logger.getLogger(Domain.class);
	
	private String descriptor;
	
	private Domain(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescription() {
		return this.name() + ": " +this.descriptor;
	}
	
	public static String printDomains() {
		String tests = "";
		for (Domain domain : Domain.values()) {
			tests += domain.getDescription();
			LOGGER.info(domain.getDescription());
		}
		return tests;
	}
	
	public static Domain getDomain() {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(InputScanner.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException iae) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}
}
