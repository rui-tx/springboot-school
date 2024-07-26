package com.ruitx.formation.aspect;

import com.ruitx.formation.model.Event;
import com.ruitx.formation.model.EventType;
import com.ruitx.formation.service.EventService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Aspect
public class EventAspect {
    private final EventService eventService;

    @Autowired
    public EventAspect(EventService eventService) {
        this.eventService = eventService;
    }

    @Before("execution(* com.ruitx.formation.controller.*.*(..))")
    public void logControllerActionToDB(JoinPoint joinPoint) {
        Event event = new Event();
        event.setEventType(EventType.CONTROLLER);
        event.setEventName(joinPoint.getSignature().getName());
        event.setEventDate(LocalDate.now());
        event.setEventTime(LocalDateTime.now());
        event.setCreatedAt(Instant.now());
        eventService.create(event);
    }

}
