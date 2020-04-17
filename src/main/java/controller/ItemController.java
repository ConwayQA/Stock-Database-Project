package controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Item;
import service.CrudService;
import utilities.InputScanner;

public class ItemController implements CrudController<Item>{
	
public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
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
	public Item read() {
		LOGGER.info("Please enter the item's ID: ");
		Long itemID = Long.valueOf((getInput()));
		Item singleItem = itemServices.read(itemID);
		LOGGER.info(singleItem.toString());
		return singleItem;
	}

	@Override
	public Item create() {
		Item tempItem = inputItemData();
		Item item = itemServices.create(new Item(tempItem.getName(), tempItem.getPrice(), tempItem.getGenre(), tempItem.getMinPlayers(), tempItem.getMaxPlayers(), tempItem.getAvgPlayTime()));
		LOGGER.info("Item created.");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Long.valueOf(getInput());
		Item tempItem = inputItemData();
		Item item = itemServices.create(new Item(id, tempItem.getName(), tempItem.getPrice(), tempItem.getGenre(), tempItem.getMinPlayers(), tempItem.getMaxPlayers(), tempItem.getAvgPlayTime()));
		LOGGER.info("Item updated.");
		return item;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemServices.delete(id);
	}
	
	public Item inputItemData() {
		LOGGER.info("Please enter the items name: ");
		String name = getInput();
		LOGGER.info("Please enter the items price: ");
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(getInput()));
		LOGGER.info("Please enter the items genre: ");
		String genre = getInput();
		LOGGER.info("Please enter the items minimum number of players: ");
		Long minPlayers = Long.valueOf((getInput()));
		LOGGER.info("Please enter the items maximum number of players: ");
		Long maxPlayers = Long.valueOf((getInput()));
		LOGGER.info("Please enter the average play time (Minutes): ");
		Long avgPlayTime = Long.valueOf((getInput()));
		return new Item(name, price, genre, minPlayers, maxPlayers, avgPlayTime);
	}

}
