package lt.ku.hotel.controller;

import lt.ku.hotel.entities.Booking;
import lt.ku.hotel.entities.Client;
import lt.ku.hotel.entities.Room;
import lt.ku.hotel.services.BookingService;
import lt.ku.hotel.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


@Controller
public class BookingController {
	@Autowired
	RoomService roomService;
	
	
	@Autowired
	BookingService bookingService;
	//@RequestMapping(value="room", method = RequestMethod.GET)

	@GetMapping("/reserve")
	public String getReservation(Model model,
			@RequestParam(required = false, value = "roomId") Integer roomId,
			@RequestParam(required = false, value = "arrivalDate") String arrival,
			@RequestParam(required = false, value = "departureDate") String departure,
			@RequestParam(required = false, value = "guestCount") Integer guestCount){
		try {
			if(roomId == null || arrival == null || departure == null || guestCount == null) {
				return "redirect:/";
				}
			boolean isReserved = roomService.isRoomReserved(roomId, arrival, departure, guestCount);
			if(isReserved) return "redirect:/";
			Room room = roomService.getRoom(roomId);
			model.addAttribute("roomId", roomId);
			model.addAttribute("roomPrice", room.getPrice());
			model.addAttribute("arrivalDate", arrival);
			model.addAttribute("departureDate", departure);
			model.addAttribute("guestCount", guestCount);
			long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(arrival), LocalDate.parse(departure));
			model.addAttribute("dayCount", noOfDaysBetween);

			return "reservation";
		}catch(DateTimeParseException e) {
			return "redirect:/";
		}
		
	}
	@PostMapping("/reserve")
	public String addReservation(Model model,
			@RequestParam(value = "roomId") Integer roomId,
			@RequestParam(value = "arrivalDate") String arrival,
			@RequestParam(value = "departureDate") String departure,
			@RequestParam(value = "guestCount") Integer guestCount,
			@RequestParam(required = false, value = "services") Double services,
			@RequestParam(required = false, value = "meals") Double meals) {
		try {
			final BigDecimal AdditionalServicePrice = new BigDecimal("20.00"); 
			System.out.println(roomId + ' ' + arrival);
			if(roomId == null || arrival == null || departure == null || guestCount == null) {
			return "redirect:/";
			}
			boolean isReserved = roomService.isRoomReserved(roomId, arrival, departure, guestCount);
			if(isReserved) return "redirect:/";
			long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(arrival), LocalDate.parse(departure));
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Room room = roomService.getRoom(roomId);
			BigDecimal totalPrice = room.getPrice();
			boolean isMealsChecked = false;
			boolean isServicesChecked = false;
			
			totalPrice = totalPrice.multiply(new BigDecimal(noOfDaysBetween));
			if(services != null) {
				isServicesChecked = true;
				totalPrice = totalPrice.add(AdditionalServicePrice);
			} 
			if(meals != null) {
				isMealsChecked = true;
				totalPrice = totalPrice.add(AdditionalServicePrice);
			}
			
			Client client = (Client) authentication.getPrincipal(); //atkomentuoti jei yra norima gaut PRISIJUNGUSIO kliento id
			Integer userId = client.getId();
			long day_count = ChronoUnit.DAYS.between(LocalDate.parse(arrival), LocalDate.parse(departure));
			bookingService.addBooking(new Booking(LocalDate.parse(arrival), userId, roomId, LocalDate.parse(departure), day_count, isServicesChecked, isMealsChecked,totalPrice));
			return "redirect:/";
			
		}catch(DateTimeParseException e) {
			return "redirect:/";
		}
		
		
		
	}
	@GetMapping("/reservations")
	public String getReservations(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Client client = (Client) authentication.getPrincipal(); //atkomentuoti jei yra norima gaut PRISIJUNGUSIO kliento id
		Integer userId = client.getId();
		model.addAttribute("booking", bookingService.getClientBookings(userId));
		return "reservations";
	}
}
