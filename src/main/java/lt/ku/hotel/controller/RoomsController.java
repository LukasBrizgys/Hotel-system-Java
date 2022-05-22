package lt.ku.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.hotel.services.RoomService;

@Controller
public class RoomsController {
	@Autowired
	RoomService roomService;
	
	
	@GetMapping("/rooms")
	public String allUnreservedRoomsList(Model model,
			@RequestParam(required = false) String arrival,
			@RequestParam(required = false) String departure,
			@RequestParam(required = false) String guestCount) {
		if(arrival == null || departure == null || guestCount == null) {
			return "redirect:/";
		}
		model.addAttribute("arrivalDate", arrival);
		model.addAttribute("departureDate", departure);
		model.addAttribute("guestCount", guestCount);
		model.addAttribute("rooms", roomService.getAllUnreservedRooms(arrival, departure, guestCount));
		
		return "room_list";
	}
	
	@GetMapping("/room/{id}")
	public String showRoomInfo(@PathVariable("id") Integer id,
			@RequestParam(required = false) String arrivalDate,
			@RequestParam(required = false) String departureDate,
			@RequestParam(required = false) String guestCount,
			Model model
			) {
		if(arrivalDate == null || departureDate == null || guestCount == null) {
			return "redirect:/";
		}
		
		model.addAttribute("roomAttributes", roomService.getRoom(id));
		model.addAttribute("features", roomService.getAllRoomFeatures(id));
		model.addAttribute("pictures", roomService.getAllRoomPictures(id));
		model.addAttribute("arrivalDate", arrivalDate);
		model.addAttribute("departureDate", departureDate);
		model.addAttribute("guestCount", guestCount);
		
		return "room_info";
	}
}
