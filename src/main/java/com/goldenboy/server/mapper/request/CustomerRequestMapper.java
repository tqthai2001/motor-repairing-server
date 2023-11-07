package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.payload.crudrequest.CustomerRequest;
import com.goldenboy.server.repository.CustomerRepository;
import com.goldenboy.server.repository.MotorbikeRepository;
import com.goldenboy.server.service.CustomerService;
import com.goldenboy.server.service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MotorbikeRepository motorbikeRepository;
    @Autowired
    private MotorbikeService motorbikeService;
    @Autowired
    private CustomerService customerService;

    public Customer toCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        return customer;
    }
}
