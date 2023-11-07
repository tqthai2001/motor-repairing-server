package com.goldenboy.server.controller;

import com.goldenboy.server.mapper.dto.TicketUpdateHistoryDTOMapper;
import com.goldenboy.server.service.TicketUpdateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/ticketUpdateTrack")
public class TicketUpdateHistoryController {
    @Autowired
    private TicketUpdateHistoryDTOMapper dtoMapper;
    @Autowired
    private TicketUpdateHistoryService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_CASHIER')")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(dtoMapper.toTicketUpdateHistoryDTOs(service.findAll()));
    }

    @GetMapping("/ticket/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_CASHIER')")
    public ResponseEntity<?> allByTicketId(@PathVariable(value = "id") Long ticketId) {
        return ResponseEntity.ok().body(dtoMapper.toTicketUpdateHistoryDTOs(service.findByTicketId(ticketId)));
    }
}
