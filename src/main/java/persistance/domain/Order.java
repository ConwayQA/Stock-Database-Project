package persistance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private Long id;
	private Long customerID;
	private List<Long> itemIDs = new ArrayList<>();
	private BigDecimal totalPrice;
	private LocalDate date;
	private Long userID;
	
	public Order(Long id, Long customerID, List<Long> itemIDs, Long userID) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.itemIDs = itemIDs;
	}
	
	public Order(Long customerID, List<Long> itemIDs, Long userID) {
		super();
		this.customerID = customerID;
		this.itemIDs = itemIDs;
	}
	
	public Order(Long customerID, List<Long> itemIDs, BigDecimal totalPrice, LocalDate date, Long userID) {
		super();
		this.customerID = customerID;
		this.itemIDs = itemIDs;
		this.totalPrice = totalPrice;
		this.date = date;
		this.userID = userID;
	}
	
	public Order(Long id, Long customerID, List<Long> itemIDs, BigDecimal totalPrice, LocalDate date, Long userID) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.itemIDs = itemIDs;
		this.totalPrice = totalPrice;
		this.date = date;
		this.userID = userID;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public List<Long> getItemIDs() {
		return itemIDs;
	}

	public void setItemIDs(List<Long> itemIDs) {
		this.itemIDs = itemIDs;
	}
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Order ID: " + id + " Customer ID: " + customerID + " Total Price: £" + totalPrice +
				" Date Ordered: " + date + " userID: " + userID;
	}
	
	//Method from christophperrins to override hashcode()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((itemIDs == null) ? 0 : itemIDs.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
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
		if (itemIDs.isEmpty()) {
			if(!compareOrder.itemIDs.isEmpty()){
				return false;
			}
		} else {
			try {
				for (int i = 0; i < itemIDs.size(); i++) {
					if (itemIDs.get(i) != compareOrder.itemIDs.get(i)) {
						return false;
					}
				}
			} catch (NullPointerException npe) {
				return false;
			}
			
		}
		if (userID == null) {
			if(compareOrder.userID != null) {
				return false;
			}
		} else if (!userID.equals(compareOrder.userID)) {
			return false;
		}
		return true;
	}
	

}
