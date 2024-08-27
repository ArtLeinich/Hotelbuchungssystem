package com.example.hotelbuchungsapp.repository;

import com.example.hotelbuchungsapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Standard CRUD-Methoden werden von JpaRepository bereitgestellt
}
