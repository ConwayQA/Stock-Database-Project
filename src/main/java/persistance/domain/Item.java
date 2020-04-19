package persistance.domain;

import java.math.BigDecimal;

public class Item {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private String genre;
	private Long minPlayers;
	private Long maxPlayers;
	private Long avgPlayTime;
	private Long userID;
	
	
	public Item(String name, BigDecimal price, String genre, Long minPlayers, Long maxPlayers, Long avgPlayTime) {
		super();
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.avgPlayTime = avgPlayTime;
	}
	
	public Item(Long id, String name, BigDecimal price, String genre, Long minPlayers, Long maxPlayers, Long avgPlayTime) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.avgPlayTime = avgPlayTime;
	}
	
	public Item(String name, BigDecimal price, String genre, Long minPlayers, Long maxPlayers, Long avgPlayTime, Long userID) {
		super();
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.avgPlayTime = avgPlayTime;
		this.userID = userID;
	}
	
	public Item(Long id, String name, BigDecimal price, String genre, Long minPlayers, Long maxPlayers, Long avgPlayTime, Long userID) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.avgPlayTime = avgPlayTime;
		this.userID = userID;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Long getMinPlayers() {
		return minPlayers;
	}
	public void setMinPlayers(Long minPlayers) {
		this.minPlayers = minPlayers;
	}
	public Long getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(Long maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public Long getAvgPlayTime() {
		return avgPlayTime;
	}
	public void setAvgPlayTime(Long avgPlayTime) {
		this.avgPlayTime = avgPlayTime;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Name: " + name + " price: £" + price +
				" genre: " + genre + " minimum number of players: " + minPlayers + 
				" maximum number of players: " + maxPlayers + " average play time: " +
				avgPlayTime + " userID: " + userID;
	}
	
	//Method from christophperrins to override hashcode()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((minPlayers == null) ? 0 : minPlayers.hashCode());
		result = prime * result + ((maxPlayers == null) ? 0 : maxPlayers.hashCode());
		result = prime * result + ((avgPlayTime == null) ? 0 : avgPlayTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Item compareItem = (Item) obj;
		if (id == null) {
			if(compareItem.id != null) {
				return false;
			}
		} else if (!id.equals(compareItem.id)) {
			return false;
		}
		if (name == null) {
			if(compareItem.name != null) {
				return false;
			}
		} else if (!name.equals(compareItem.name)) {
			return false;
		}
		if (price == null) {
			if(compareItem.price != null) {
				return false;
			}
		} else if (!price.equals(compareItem.price)) {
			return false;
		}
		if (minPlayers == null) {
			if(compareItem.minPlayers != null) {
				return false;
			}
		} else if (!minPlayers.equals(compareItem.minPlayers)) {
			return false;
		}
		if (maxPlayers == null) {
			if(compareItem.maxPlayers != null) {
				return false;
			}
		} else if (!maxPlayers.equals(compareItem.maxPlayers)) {
			return false;
		}
		if (avgPlayTime == null) {
			if(compareItem.avgPlayTime != null) {
				return false;
			}
		} else if (!avgPlayTime.equals(compareItem.avgPlayTime)) {
			return false;
		}
		if (genre == null) {
			if(compareItem.genre != null) {
				return false;
			}
		} else if (!genre.equals(compareItem.genre)) {
			return false;
		}
		if (userID == null) {
			if(compareItem.userID != null) {
				return false;
			}
		} else if (!userID.equals(compareItem.userID)) {
			return false;
		}
		return true;
	}

}
