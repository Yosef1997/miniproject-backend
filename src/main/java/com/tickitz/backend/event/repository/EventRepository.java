package com.tickitz.backend.event.repository;

import com.tickitz.backend.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

}
