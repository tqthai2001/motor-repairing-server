package com.goldenboy.server.controller;

import com.goldenboy.server.common.SearchParamParser;
import com.goldenboy.server.controller.base.BaseController;
import com.goldenboy.server.mapper.dto.EmployeeDTOMapper;
import com.goldenboy.server.mapper.dto.TicketDTOMapper;
import com.goldenboy.server.mapper.request.EmployeeRequestMapper;
import com.goldenboy.server.mapper.request.SearchRequestMapper;
import com.goldenboy.server.payload.crudrequest.EmployeeRequest;
import com.goldenboy.server.security.service.UserDetailsImpl;
import com.goldenboy.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class EmployeeController implements BaseController<EmployeeRequest> {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRequestMapper requestMapper;
    @Autowired
    private EmployeeDTOMapper dtoMapper;
    @Autowired
    private TicketDTOMapper ticketDTOMapper;
    @Autowired
    private SearchRequestMapper searchRequestMapper;
    @Autowired
    private SearchParamParser searchParamParser;

    @Override
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(dtoMapper.toEmployeeDTOs(employeeService.findAll()));
    }

    @Override
    @GetMapping("/employees/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> one(Long id) {
        return ResponseEntity.ok().body(dtoMapper.toEmployeeDTO(employeeService.findById(id)));
    }

    @Override
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeRequest entityRequest) {
        return ResponseEntity.ok(dtoMapper.toEmployeeDTO(employeeService.save(requestMapper.toEmployee(entityRequest))));
    }

    @Override
    @PutMapping("/employees/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(EmployeeRequest entity, Long id) {
        return ResponseEntity.ok(dtoMapper.toEmployeeDTO(employeeService.updateById(id,
                                                                                    requestMapper.toEmployee(entity))));
    }

    @Override
    @DeleteMapping("/employees/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(Long id) {
        employeeService.deleteByIdTmp(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/employees/delete_multi")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMulti(@RequestBody List<Long> ids) {
        employeeService.deleteByIdArrayTmp(ids);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/employees/paging")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> all(int page, int size) {
        return ResponseEntity.ok(employeeService.findAllPaging(page, size));
    }

    @GetMapping("/employees/f")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> search(@RequestParam(value = "search", required = false) String search) {
        return ResponseEntity.ok()
                             .body(dtoMapper.toEmployeeDTOs(employeeService.searchEmployee(searchRequestMapper.toSearchCriterias(searchParamParser.toSearchString(search)))));
    }

    @GetMapping("/employees/{id}/tickets")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> allTickets(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(ticketDTOMapper.toTicketDTOs(employeeService.findAllTicket(id)));
    }

    @PostMapping("/employees/updatePassword")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_CASHIER')")
    public ResponseEntity<?> updatePassword(@RequestParam("password") String password,
                                            @RequestParam("oldPassword") String oldPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if (employeeService.updatePassword(user.getUsername(), oldPassword, password, user.getPassword())) {
            return ResponseEntity.accepted().body("Cập nhật mật khẩu thành công!");
        } else {
            return ResponseEntity.accepted().body("Cập nhật mật khẩu thất bại!");
        }
    }
}
