package org.example.controller;

import org.example.model.CoworkingSpace;
import org.example.model.Reservation;
import org.example.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final ReservationService reservationService;

    public CustomerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/panel")
    public String customerPanel() {
        return "customer/panel";
    }

    @GetMapping("/view-spaces")
    public String viewAvailableSpaces(Model model){
        List<CoworkingSpace> spaces = reservationService.viewSpaces();
        model.addAttribute("spaces", spaces);
        return "customer/view-spaces";
    }

    @GetMapping("/book-space")
    public String bookSpaceForm(@RequestParam(name = "id", required = false)Integer id, Model model){
        if(id!= null){
            CoworkingSpace space = reservationService.findSpaceID(id);
            Reservation reservation = new Reservation();
            reservation.setSpace(space);
            model.addAttribute("reservation", reservation);
        }else {
            model.addAttribute("reservation", new Reservation());
        }
        return "customer/book-space";
    }

    @PostMapping("/book")
    public String bookSpace(@ModelAttribute Reservation reservation) {
        reservationService.bookSpace(reservation);
        return "redirect:/customer/book-space";
    }

    @GetMapping("/my-bookings")
    public String myBookings(Model model){
        model.addAttribute("bookings", reservationService.myBookings(0));
        return "customer/my-bookings";
    }

    @GetMapping("/cancel-booking")
    public String cancelBookingForm() {
        return "customer/cancel-booking";
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam ("bookingID")int bookingId, Model model) {
        boolean canceled = reservationService.cancelBooking(bookingId);
        model.addAttribute("message", canceled ? "Booking canceled" : "Failed to cancel booking");
        return "redirect:/customer/my-bookings";
    }

}
