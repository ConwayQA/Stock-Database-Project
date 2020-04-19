package mainer;

import org.apache.log4j.Logger;

import controller.ControllerActions;
import controller.CrudController;
import controller.CustomerController;
import controller.ItemController;
import controller.LoginController;
import controller.OrderController;
import controller.UserLoginController;
import persistance.dao.CustomerDAO;
import persistance.dao.DAOConnect;
import persistance.dao.ItemDAO;
import persistance.dao.OrderDAO;
import persistance.dao.UserLoginDAO;
import persistance.domain.Domain;
import persistance.domain.User;
import service.CustomerServices;
import service.ItemServices;
import service.OrderServices;
import service.UserLoginServices;
import utilities.InputScanner;

public class StockManager {
	
	public boolean endApp = true;
	public User currentUser;
	public static final Logger LOGGER = Logger.getLogger(StockManager.class);
	
	public void stockSystemRun() {
		LOGGER.info("Enter Admin username");
		String username = InputScanner.getInput();
		LOGGER.info("Enter Admin password");
		String password = InputScanner.getInput();

		DAOConnect.setUsername(username);
		DAOConnect.setPassword(password);
		
		LOGGER.info("Would you like to reset the databases? [Y/N]");
		String resetter = InputScanner.getInput();
		if (resetter.toUpperCase().contentEquals("Y")) {
			DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
		}
				
		
		currentUser = userLogin();
		if (currentUser.isLoggedIn()) {
			endApp = false;
		}
		
		//temp endApp Change for testing.
		endApp = false;
		
		while(!endApp) {
			runMenu(currentUser.getUserID());
		};
	}
	
	public void runMenu(Long userID) {
		LOGGER.info("Which entity would you like to use?");
		Domain.printDomains();
		Domain domain = Domain.getDomain();
		
		if (domain != Domain.END) {
			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");
			ControllerActions.printActions();
			ControllerActions action = ControllerActions.getControllerAction();
			
			switch (domain) {
			case CUSTOMER:
				CustomerController customerController = new CustomerController(new CustomerServices(new CustomerDAO()));
				doAction(customerController, action, userID);
				break;
			case ITEMS:
				ItemController itemController = new ItemController(new ItemServices(new ItemDAO()));
				doAction(itemController, action, userID);
				break;
			case ORDERS:
				OrderController orderController = new OrderController(new OrderServices(new OrderDAO()));
				doAction(orderController, action, userID);
				break;
			case END:
				break;
			default:
				break;
			}
		} else { endApp = true; }
	}
	
	public void doAction(CrudController<?> crudController, ControllerActions action, Long userID) {
		switch (action) {
		case CREATE:
			crudController.create(userID);
			break;
		case READ:
			crudController.read();
			break;
		case READALL:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update(userID);
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}
	
	public User userLogin() {
		LoginController<User> userCrudController = new UserLoginController(new UserLoginServices(new UserLoginDAO()));
		return userCrudController.login();
	}

}
