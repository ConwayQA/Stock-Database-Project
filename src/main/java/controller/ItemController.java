package controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Item;
import service.CrudService;
import utilities.InputScanner;

public class ItemController implements CrudController<Item>{
	
public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudService<Item> itemServices;
	
	public ItemController(CrudService<Item> itemServices) {
		this.itemServices = itemServices;
	}
	
	String getInput() {
		return InputScanner.getInput();
	}

	@Override
	public List<Item> readAll() {
		List<Item> allItems = itemServices.readAll();
		for(Item item: allItems) {
			LOGGER.info(item.toString());
		}
		return allItems;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter the items name: ");
		String name = getInput();
		LOGGER.info("Please enter the items price: ");
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(getInput()));
		LOGGER.info("Please enter the items genre: ");
		String genre = getInput();
		LOGGER.info("Please enter the items minimum number of players: ");
		Long minPlayers = Long.getLong(getInput());
		LOGGER.info("Please enter the maximum number of players: ");
		Long maxPlayers = Long.getLong(getInput());
		LOGGER.info("Please enter the average play time (Minutes): ");
		Long avgPlayTime = Long.getLong(getInput());
		Item item = itemServices.create(new Item(name, price, genre, minPlayers, maxPlayers, avgPlayTime));
		LOGGER.info("Item created.");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the items name: ");
		String name = getInput();
		LOGGER.info("Please enter the items price: ");
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(getInput()));
		LOGGER.info("Please enter the items genre: ");
		String genre = getInput();
		LOGGER.info("Please enter the items minimum number of players: ");
		Long minPlayers = Long.getLong(getInput());
		LOGGER.info("Please enter the maximum number of players: ");
		Long maxPlayers = Long.getLong(getInput());
		LOGGER.info("Please enter the average play time (Minutes): ");
		Long avgPlayTime = Long.getLong(getInput());
		Item item = itemServices.create(new Item(id, name, price, genre, minPlayers, maxPlayers, avgPlayTime));
		LOGGER.info("Item created.");
		return item;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemServices.delete(id);
	}

}
