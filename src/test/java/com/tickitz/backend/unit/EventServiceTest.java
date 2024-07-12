package com.tickitz.backend.unit;

import com.tickitz.backend.event.dto.CreateEventRequestDto;
import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.dto.UpdateEventRequestDto;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.review.service.ReviewService;
import com.tickitz.backend.ticket.service.TicketService;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTest {

  @Autowired
  private EventService eventService;
  @MockBean
  private UsersService usersService;
  @MockBean
  private ReviewService reviewService;
  @MockBean
  private TicketService ticketService;
  @MockBean
  private PromotionService promotionService;
  Event event;
  Users user = new Users();
  CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto();
  UpdateEventRequestDto updateEventRequestDto = new UpdateEventRequestDto();

  @BeforeEach
  void setup() {
    user = usersService.getDetailUserId(2454L);
    createEventRequestDto.setEventName("Sample Event");
    createEventRequestDto.setEventImage("https://res.cloudinary.com/dhbg53ncx/image/upload/v1719926382/j3igaspvfztc6zgbxd1t.jpg");
    createEventRequestDto.setCategory("Music");
    createEventRequestDto.setLocation("Jakarta");
    createEventRequestDto.setVenue("Gelora Bung Karno");
    createEventRequestDto.setDescription("description");
    createEventRequestDto.setDate("2024-07-12");
    createEventRequestDto.setStartTime("2024-07-01T18:00:00Z");
    createEventRequestDto.setEndTime("2024-07-01T21:00:00Z");
    createEventRequestDto.setUserId(2454L);



    updateEventRequestDto.setId(852L);
    updateEventRequestDto.setEventName("Update Sample Event");
    updateEventRequestDto.setEventImage("https://res.cloudinary.com/dhbg53ncx/image/upload/v1719926382/j3igaspvfztc6zgbxd1t.jpg");
    updateEventRequestDto.setCategory("Music");
    updateEventRequestDto.setLocation("Jakarta");
    updateEventRequestDto.setVenue("Gelora Bung Karno");
    updateEventRequestDto.setDescription("description");
    updateEventRequestDto.setDate("2024-07-12");
    updateEventRequestDto.setStartTime("2024-07-01T18:00:00Z");
    updateEventRequestDto.setEndTime("2024-07-01T21:00:00Z");
    updateEventRequestDto.setUserId(2454L);
  }


  @Test
  void testGetAllEvents() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Event> result = eventService.getAllEvents(pageable, null, null, null);

    assertNotNull(result);
    assertEquals(10, result.getSize());
  }

  @Test
  void testGetDetailEvent() {
    EventResponseDto result = eventService.getDetailEvent(952L);

    assertNotNull(result);
    assertEquals(952, result.getId());
  }

  @Test
  void testCreateEvent() {
    EventResponseDto result = eventService.createEvent(createEventRequestDto);

    assertNotNull(result);
    assertEquals("Sample Event", result.getEventName());
  }

  @Test
  void testUpdateEvent() {
    EventResponseDto result = eventService.updateEvent(updateEventRequestDto);
    assertNotNull(result);
    assertEquals("Update Sample Event", result.getEventName());  }

  @Test
  void testDeleteEvent() {
    String result = eventService.deleteEvent(953L);

    assertEquals("Delete event success", result);
  }
}
