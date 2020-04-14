package service;

import java.util.List;

import persistance.dao.DAO;
import persistance.domain.Item;

public class ItemServices implements CrudService<Item>{
	
	private DAO<Item> itemDAO;
	
	public ItemServices(DAO<Item> itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public List<Item> readAll() {
		return itemDAO.readAll();
	}

	@Override
	public Item create(Item createItem) {
		return itemDAO.create(createItem);
	}

	@Override
	public Item update(Item updateItem) {
		return itemDAO.update(updateItem);
	}

	@Override
	public void delete(Long id) {
		itemDAO.delete(id);
	}
	
	

}
