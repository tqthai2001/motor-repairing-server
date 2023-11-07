package com.goldenboy.server.controller;

import com.goldenboy.server.common.SearchParamParser;
import com.goldenboy.server.controller.base.BaseController;
import com.goldenboy.server.mapper.dto.MotorbikeDTOMapper;
import com.goldenboy.server.mapper.dto.TicketDTOMapper;
import com.goldenboy.server.mapper.request.MotorbikeRequestMapper;
import com.goldenboy.server.mapper.request.SearchRequestMapper;
import com.goldenboy.server.payload.crudrequest.MotorbikeRequest;
import com.goldenboy.server.service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/motorbikes")
public class MotorbikeController implements BaseController<MotorbikeRequest> {
    @Autowired
    private MotorbikeService motorbikeService;
    @Autowired
    private MotorbikeDTOMapper dtoMapper;
    @Autowired
    private MotorbikeRequestMapper requestMapper;
    @Autowired
    private TicketDTOMapper ticketDTOMapper;
    @Autowired
    private SearchRequestMapper searchRequestMapper;
    @Autowired
    private SearchParamParser searchParamParser;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(dtoMapper.toMotorbikeDTOs(motorbikeService.findAll()));
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> one(Long id) {
        return ResponseEntity.ok().body(dtoMapper.toMotorbikeDTO(motorbikeService.findById(id)));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> create(MotorbikeRequest entity) {
        return ResponseEntity.ok()
                             .body(dtoMapper.toMotorbikeDTO(motorbikeService.save(requestMapper.toMotorbike(entity))));
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(MotorbikeRequest entity, Long id) {
        return ResponseEntity.ok()
                             .body(dtoMapper.toMotorbikeDTO(motorbikeService.updateById(id,
                                                                                        requestMapper.toMotorbike(entity))));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> delete(Long id) {
        motorbikeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/paging")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> all(int page, int size) {
        return ResponseEntity.ok().body(motorbikeService.findAllPaging(page, size));
    }

    @GetMapping("/f")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> search(@RequestParam(required = false, value = "search") String search) {
        return ResponseEntity.ok()
                             .body(dtoMapper.toMotorbikeDTOs(motorbikeService.searchMotorbike(searchRequestMapper.toSearchCriterias(searchParamParser.toSearchString(search)))));
    }

    @GetMapping("/{id}/tickets")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> allTickets(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(ticketDTOMapper.toTicketDTOs(motorbikeService.findAllTicket(id)));
    }
}
