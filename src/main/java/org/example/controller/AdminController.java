package org.example.controller;

import org.example.model.CoworkingSpace;
import org.example.service.CoworkingSpaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CoworkingSpaceService spaceService;

    public AdminController(CoworkingSpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/panel")
    public String adminPanel(){
        return "admin/panel";
    }


    @GetMapping("/spaces/add")
    public String addSpaceForm(Model model){
        model.addAttribute("space", new CoworkingSpace());
        return "admin/add-space";
    }

    @PostMapping("/spaces/add")
    public String addSpace(@ModelAttribute CoworkingSpace space){
        spaceService.addSpace(space);
        return "redirect:/admin/add-space";
    }

     @GetMapping("/spaces/remove")
    public String removeSpaceForm(){
        return "admin/remove-space";
     }

     @PostMapping("/spaces/remove")
         public String removeSpace ( @RequestParam ("spaceID")int spaceID, Model model){
             boolean removed = spaceService.removeSpace(spaceID);
             model.addAttribute("message", removed ? "Space removed successfully" : "Failed to remove space");
             return "redirect:/admin/remove-space";
         }

         @GetMapping("/bookings")
         public String viewAllBookings (Model model){
             model.addAttribute("bookings", spaceService.viewAllBookings());
             return "admin/view-bookings";
         }

     }





