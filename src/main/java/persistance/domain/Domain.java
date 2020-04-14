package persistance.domain;

public enum Domain {

	CUSTOMER("Information on store customers"),
	ITEMS("Details on items in stock"),
	ORDERS("Collection of customers purchases of items"),
	END("Closes the application once you are done");

	private Domain(String string) {
		// TODO Auto-generated constructor stub
	}

}
