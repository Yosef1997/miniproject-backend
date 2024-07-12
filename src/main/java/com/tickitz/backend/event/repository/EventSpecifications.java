package com.tickitz.backend.event.repository;

import com.tickitz.backend.event.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventSpecifications {
  public static Specification<Event> byEventName(String eventName) {
    return ((root, query, cb) -> {
      if (eventName == null) {
        return cb.conjunction();
      }
      if (eventName.isEmpty()) {
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
      return cb.equal(root.get("user").get("id"), userId);
    });
  }

  public static Specification<Event> byUpcoming(String upcoming) {
    return (root, query, cb) -> {
      if (Objects.equals(upcoming, "true")) {
        LocalDateTime targetDate = LocalDateTime.now().plusDays(30);
        return cb.greaterThanOrEqualTo(root.get("date"), targetDate);
      }
      return cb.conjunction();
    };
  }
}
