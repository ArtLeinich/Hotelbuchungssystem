package com.example.hotelbuchungsapp.controller;

import com.example.hotelbuchungsapp.entity.Room;
import com.example.hotelbuchungsapp.service.HotelService;
import com.example.hotelbuchungsapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    // Liste aller Zimmer
    @GetMapping
    public String listRooms(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    // Anzeige des Formulars zum Anlegen eines neuen Zimmers
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelService.findAll()); // Übergabe der Liste der Hotels an das Modell
        return "room-form";
    }

    // Speichern eines neuen Zimmers
    @PostMapping ("/save")
    public String saveRoom(@ModelAttribute("room") Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    // Anzeige des Formulars zur Bearbeitung eines Raums
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Room room = roomService.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            model.addAttribute("hotels", hotelService.findAll()); // Übergabe der Liste der Hotels an das Modell
            return "room-form";
        } else {
            return "error"; // Anzeige der Fehlerseite, wenn der Raum nicht gefunden wird
        }
    }

    // Aktualisierung eines bestehenden Zimmers
    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute("room") Room room) {
        // Einen vorhandenen Zimmer anhand der ID finden
        Room existingRoom = roomService.findById(id);
        if (existingRoom != null) {
            // Aktualisieren Sie die Felder des bestehenden Zimmers mit neuen Daten
            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setHotel(room.getHotel());
            // Andere Felder nach Bedarf aktualisieren

            roomService.save(existingRoom); // Speichern des aktualisierten Zimmers
        }
        return "redirect:/rooms";
    }

    // Löschung eines Zimmers
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
        return "redirect:/rooms";
    }

    // Anzeige der Details eines bestimmten Zimmers
    @GetMapping("/{id}")
    public String viewRoom(@PathVariable Long id, Model model) {
        Room room = roomService.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room-details";
        } else {
            return "error"; // Fehlerseite anzeigen, wenn das Zimmer nicht gefunden wird
        }
    }
}
