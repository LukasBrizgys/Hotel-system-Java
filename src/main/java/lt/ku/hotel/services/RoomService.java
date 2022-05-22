package lt.ku.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.hotel.entities.Feature;
import lt.ku.hotel.entities.Picture;
import lt.ku.hotel.entities.Room;
import lt.ku.hotel.repositories.FeatureRepository;
import lt.ku.hotel.repositories.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	FeatureRepository featureRepository;
	public List<Room> getAllRooms(){
		return roomRepository.findAll();
	}
	public List<Room> getAllUnreservedRooms(String arrivalDate, String departureDate, String guestCount){
		return roomRepository.findAllUnreservedRooms(arrivalDate, departureDate, guestCount);
	}
	public Room getRoom(Integer id) {
		return roomRepository.getById(id);
	}
	public Room addRoom(Room room) {
		return roomRepository.save(room);
	}
	
	public Room updateRoom(Room room) {
		Room old = roomRepository.getById(room.getId());
		old.setDescription(room.getDescription());
		old.setGuest_limit(room.getGuest_limit());
		old.setPrice(room.getPrice());
		old.setRoom_size(room.getRoom_size());
		old.setThumbnail(room.getThumbnail());
		
		return old;
	}
	
	public void deleteRoom(Integer id) {
		roomRepository.deleteById(id);
	}
	public boolean isRoomReserved(Integer roomId, String arrivalDate, String departureDate, Integer guestCount) {
		Integer isUnreserved = roomRepository.checkIfReserved(roomId, arrivalDate, departureDate, guestCount);
		if(isUnreserved == 1) return false;
		return true;
	}
	public List<Picture> getAllRoomPictures(Integer roomId){
		return roomRepository.findAllRoomPictures(roomId);
	}
	
	public List<Feature> getAllRoomFeatures(Integer roomId){
		return featureRepository.findFeaturesByRoomsId(roomId);
	}
}
