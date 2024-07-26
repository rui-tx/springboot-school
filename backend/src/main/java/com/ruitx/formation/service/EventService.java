package com.ruitx.formation.service;

import com.ruitx.formation.model.Event;
import com.ruitx.formation.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Cacheable(value = "eventCache")
    public Event create(Event event) {
        return eventRepository.save(event);
    }
}
