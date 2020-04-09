package persistance.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private Long id;
	private Long customerID;
	private List<Long> itemIDs = new ArrayList<>();
	private BigDecimal totalPrice;
	private String date;
	
	public Order(Long id, Long customerID, BigDecimal totalPrice, String date) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.date = date;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<Long> getItemIDs() {
		return itemIDs;
	}

	public void setItemIDs(List<Long> itemIDs) {
		this.itemIDs = itemIDs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order compareOrder = (Order) obj;
		if (id == null) {
			if(compareOrder.id != null) {
				return false;
			}
		} else if (!id.equals(compareOrder.id)) {
			return false;
		}
		if (customerID == null) {
			if(compareOrder.customerID != null) {
				return false;
			}
		} else if (!customerID.equals(compareOrder.customerID)) {
			return false;
		}
		if (totalPrice == null) {
			if(compareOrder.totalPrice != null) {
				return false;
			}
		} else if (!totalPrice.equals(compareOrder.totalPrice)) {
			return false;
		}
		if (date == null) {
			if(compareOrder.date != null) {
				return false;
			}
		} else if (!date.equals(compareOrder.date)) {
			return false;
		}
		if (itemIDs == null) {
			if(compareOrder.itemIDs != null) {
				return false;
			}
		} 
		if (itemIDs.isEmpty()) {
			if(!compareOrder.itemIDs.isEmpty()){
				return false;
			}
		} else {
			try {
				for (int i = 0; i < itemIDs.size(); i++) {
					if (!(itemIDs.get(i) == compareOrder.itemIDs.get(i))) {
						return false;
					}
				}
			} catch (NullPointerException npe) {
				return false;
			}
			
		}
		return true;
	}
	

}
