package lt.ku.hotel.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String room_size;
	
	@Column
	private Integer guest_limit;
	
	@Column(columnDefinition="TEXT")
	String description;
	
	@Column(precision = 10, scale=2)
	private BigDecimal price;
	
	@Column
	private String thumbnail;
	
	
	@ManyToOne
	@JoinColumn(name="type_ID", nullable = false)
	private RoomType room_type;
	
	@OneToMany(mappedBy = "room")
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "room")
	private List<Picture> pictures;
	public Room() {
		super();
	}
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name="room_feature",
			joinColumns = { @JoinColumn(name = "roomId")},
			inverseJoinColumns = { @JoinColumn(name = "featureId")}
			)
	List<Feature> features = new ArrayList<Feature>();
	
	public Room(String room_size, Integer guest_limit, String description, BigDecimal price, String thumbnail,
			boolean is_reserved) {
		super();
		this.room_size = room_size;
		this.guest_limit = guest_limit;
		this.description = description;
		this.price = price;
		this.thumbnail = thumbnail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoom_size() {
		return room_size;
	}

	public void setRoom_size(String room_size) {
		this.room_size = room_size;
	}

	public Integer getGuest_limit() {
		return guest_limit;
	}

	public void setGuest_limit(Integer guest_limit) {
		this.guest_limit = guest_limit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public RoomType getRoom_type() {
		return room_type;
	}

	public void setRoom_type(RoomType room_type) {
		this.room_type = room_type;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	
}
