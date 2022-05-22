package lt.ku.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.hotel.entities.Booking;
import lt.ku.hotel.repositories.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository bookingRepository;
	
	public List<Booking> getAllBookings(){
		return bookingRepository.findAll();
	}
	
	public Booking getBooking(Integer id) {
		return bookingRepository.getById(id);
	}
	public Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	public List<Booking> getClientBookings(Integer id){
		return bookingRepository.findBookingsByClientId(id);
	}
}
