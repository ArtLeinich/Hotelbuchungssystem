package com.example.hotelbuchungsapp.controller;

import com.example.hotelbuchungsapp.entity.Hotel;
import com.example.hotelbuchungsapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Listet alle Hotels auf
    @GetMapping
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelService.findAll();
        model.addAttribute("hotels", hotels);
        return "hotel-list";
    }

    // Zeigt das Formular zum Erstellen eines neuen Hotels
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel-form";
    }

    // Speichert ein neues Hotel
    @PostMapping
    public String saveHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.save(hotel);
        return "redirect:/hotels";
    }

    // Zeigt das Formular zur Bearbeitung eines bestehenden Hotels
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Hotel> hotel = hotelService.findById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel-form";
        } else {
            // Fehlerbehandlungslogik, wenn kein Hotel gefunden wird
            return "error"; // man kann eine Fehlerseite zurückgeben oder auf eine andere URL umleiten
        }
    }

    // Aktualisiert ein bestehendes Hotel
    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute("hotel") Hotel hotel) {
        Optional<Hotel> existingHotel = hotelService.findById(id);
        if (existingHotel.isPresent()) {
            Hotel hotelToUpdate = existingHotel.get();
            hotelToUpdate.setName(hotel.getName());
            hotelToUpdate.setAddress(hotel.getAddress());
            hotelToUpdate.setCity(hotel.getCity());
            hotelToUpdate.setCountry(hotel.getCountry());

            // Weitere Felder nach Bedarf aktualisieren
            hotelService.save(hotelToUpdate);
        }
        return "redirect:/hotels";
    }


    // Löscht ein Hotel anhand seiner ID
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteById(id);
        return "redirect:/hotels";
    }

    // Listet alle Zimmer eines bestimmten Hotels auf
    @GetMapping("/rooms/{id}")
    public String listHotelRooms(@PathVariable Long id, Model model) {
        Optional<Hotel> hotel = hotelService.findById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            model.addAttribute("rooms", hotel.get().getRooms());
            return "room-list";
        } else {
            // Fehlerbehandlungslogik, wenn kein Hotel gefunden wird
            return "error";
        }
    }

    // Zeigt die Details eines bestimmten Hotels
    @GetMapping("/{id}")
    public String viewHotel(@PathVariable Long id, Model model) {
        Optional<Hotel> hotel = hotelService.findById(id);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel-details";
        } else {
            // Fehlerbehandlungslogik, wenn kein Hotel gefunden wird
            return "error";
        }
    }
}
