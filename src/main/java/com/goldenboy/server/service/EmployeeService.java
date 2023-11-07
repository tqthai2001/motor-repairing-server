package com.goldenboy.server.service;

import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService extends BaseService<Employee> {
    @Transactional
    void deleteByIdTmp(Long employeeId);

    @Transactional
    void deleteByIdArrayTmp(List<Long> employeeIds);

    List<Employee> searchEmployee(List<SearchCriteria> params);

    List<Ticket> findAllTicket(Long repairingEmployeeId);

    boolean updatePassword(String username, String oldPassword, String password, String dbHashedPassword);
}
