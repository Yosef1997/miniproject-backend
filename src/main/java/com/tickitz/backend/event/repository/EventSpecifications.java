package com.tickitz.backend.event.repository;

import com.tickitz.backend.event.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecifications {
  public static Specification<Event> byEventName(String eventName) {
    return ((root, query, cb) -> {
      if (eventName == null) {
        return cb.conjunction();
      }
      if (eventName == "") {
        return cb.equal(root.get("eventName"), "");
      }
      return cb.like(cb.lower(root.get("eventName")), "%" + eventName.toLowerCase() + "%");
    });
  }

  public static Specification<Event> byCategory(String category) {
    return ((root, query, cb) -> {
      if (category == null) {
        return cb.conjunction();
      }
      return cb.equal(cb.lower(root.get("category")), category.toLowerCase());
    });
  }

  public static Specification<Event> byLocation(String location) {
    return ((root, query, cb) -> {
      if (location == null) {
        return cb.conjunction();
      }
      return cb.equal(cb.lower(root.get("location")), location.toLowerCase());
    });
  }

  public static Specification<Event> byUserId(Long userId) {
    return ((root, query, cb)-> {
      if (userId == null) {
        return cb.conjunction();
      }
      return cb.equal(root.get("userId"), userId);
    });
  }

  public static Specification<Event> byUpcomingEvent() {
    return (root, query, cb) -> {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime thirtyDaysFromNow = now.plusDays(30);
      return cb.between(root.get("startDate"), now, thirtyDaysFromNow);
    };
  }
}
