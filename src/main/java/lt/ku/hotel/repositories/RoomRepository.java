package lt.ku.hotel.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lt.ku.hotel.entities.Feature;
import lt.ku.hotel.entities.Picture;
import lt.ku.hotel.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	@Query(value ="SELECT * from room\r\n"
			+ "  WHERE room.id NOT IN(\r\n"
			+ "  SELECT bookings.roomid\r\n"
			+ "  FROM bookings\r\n"
			+ "  INNER JOIN room ON bookings.roomid = room.id\r\n"
			+ "  WHERE bookings.check_out >= :arrivalDate\r\n"
			+ "  AND bookings.check_in <= :departureDate\r\n"
			+ "  ) AND room.guest_limit >= :guestCount", nativeQuery = true)
	
	List<Room> findAllUnreservedRooms(String arrivalDate, String departureDate, String guestCount);
	@Query(value ="SELECT COUNT(room.id) from room\r\n"
			+ "  WHERE room.id NOT IN(\r\n"
			+ "  SELECT bookings.roomid\r\n"
			+ "  FROM bookings\r\n"
			+ "  INNER JOIN room ON bookings.roomid = room.id\r\n"
			+ "  WHERE bookings.check_out >= :arrivalDate\r\n"
			+ "  AND bookings.check_in <= :departureDate\r\n"
			+ "  ) AND room.guest_limit >= :guestCount AND room.id = :roomId LIMIT 1", nativeQuery = true)
	Integer checkIfReserved(Integer roomId, String arrivalDate, String departureDate, Integer guestCount);
	
	@Query("SELECT p FROM Picture p LEFT JOIN p.room r WHERE r.id = :roomId")
	List<Picture> findAllRoomPictures(Integer roomId);
	
	
	List<Room> findRoomsByFeaturesId(Integer roomId);
		
	
}
