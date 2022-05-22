package lt.ku.hotel.controller;

import lt.ku.hotel.repositories.BookingRepository;
import lt.ku.hotel.services.BookingService;
import lt.ku.hotel.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Controller
public class BookingController {
	private BookingRepository bookingRepository;
	private RoomService roomService;
	//@RequestMapping(value="room", method = RequestMethod.GET)
	@GetMapping("/reserve")
	public String getReservation(Model model,
								 @RequestParam(required = false, value = "arrivalDate") String arrival,
								 @RequestParam(required = false, value = "departureDate") String departure,
								 @RequestParam(required = false, value = "guestCount") String guestCount) throws ParseException {
		model.addAttribute("arrivalDate", arrival);
		model.addAttribute("departureDate", departure);
		model.addAttribute("guestCount", guestCount);
		long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(arrival), LocalDate.parse(departure));
		//System.out.println(noOfDaysBetween);
		model.addAttribute("dayCount", noOfDaysBetween);
		return "reservation";
	}
	@PostMapping("/reserve")
	public String addReservation(Model model) {
		return "redirect:/reservations/";
	}
	@GetMapping("/reservations")
	public String getReservations(Model model){
		return "reservations";
	}
}
