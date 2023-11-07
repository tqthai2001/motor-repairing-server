package com.goldenboy.server.service;

import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService extends BaseService<Customer> {
    @Transactional
    void deleteByIdTmp(Long customerId);

    @Transactional
    void deleteByIdArrayTmp(List<Long> customerIds);

    List<Motorbike> getAllMotorbikesByCustomersId(Long customerId);

    void deleteMotorbikeFromCustomer(Long motorbikeId, Long customerId);

    Motorbike addMotorbikeToCustomer(Long motorbikeId, Long customerId);

    List<Customer> searchCustomer(List<SearchCriteria> params);

    List<Ticket> findAllTicket(Long customerId);
}
