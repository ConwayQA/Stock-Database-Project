package mainer;

import org.apache.log4j.Logger;

import controller.ControllerActions;
import controller.CrudController;
import controller.CustomerController;
import controller.ItemController;
import controller.OrderController;
import persistance.dao.CustomerDAO;
import persistance.dao.DAOConnect;
import persistance.dao.ItemDAO;
import persistance.dao.OrderDAO;
import persistance.domain.Domain;
import service.CustomerServices;
import service.ItemServices;
import service.OrderServices;
import utilities.InputScanner;

public class StockManager {
	
	public boolean endApp = false;
	public static final Logger LOGGER = Logger.getLogger(StockManager.class);
	
	public void stockSystemRun() {
		LOGGER.info("Enter Admin username");
		String username = InputScanner.getInput();
		LOGGER.info("Enter Admin password");
		String password = InputScanner.getInput();

		DAOConnect.setUsername(username);
		DAOConnect.setPassword(password);
		
		do {
			runMenu();
		} while(!endApp);
	}
	
	public void runMenu() {
		LOGGER.info("Which entity would you like to use?");
		Domain.printDomains();

		Domain domain = Domain.getDomain();
		LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

		ControllerActions.printActions();
		ControllerActions action = ControllerActions.getControllerAction();

		switch (domain) {
		case CUSTOMER:
			CustomerController customerController = new CustomerController(
					new CustomerServices(new CustomerDAO()));
			doAction(customerController, action);
			break;
		case ITEMS:
			ItemController itemController = new ItemController(
					new ItemServices(new ItemDAO()));
			doAction(itemController, action);
			break;
		case ORDERS:
			OrderController orderController = new OrderController(
					new OrderServices(new OrderDAO()));
			doAction(orderController, action);
			break;
		case END:
			endApp = true;
			break;
		default:
			break;
		}
	}
	
	public void doAction(CrudController<?> crudController, ControllerActions action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.read();
			break;
		case READALL:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
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

}
