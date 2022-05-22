package lt.ku.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.hotel.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findBookingsByClientId(Integer id);
}
