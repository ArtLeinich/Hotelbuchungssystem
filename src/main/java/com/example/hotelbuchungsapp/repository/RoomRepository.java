package com.example.hotelbuchungsapp.repository;

import com.example.hotelbuchungsapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // Standard CRUD-Methoden werden von JpaRepository bereitgestellt
}
