package com.goldenboy.server.mapper.request;

import com.goldenboy.server.common.RandomCodeGenerator;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.payload.crudrequest.TicketRequest;
import com.goldenboy.server.repository.CustomerRepository;
import com.goldenboy.server.repository.EmployeeRepository;
import com.goldenboy.server.repository.MotorbikeRepository;
import com.goldenboy.server.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketRequestMapper {
    @Autowired
    private MotorbikeRepository motorbikeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket toNewTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        Motorbike motorbike = null;
        Customer customer = null;
        Employee repairingEmployee = null;
        String code;
        motorbike = motorbikeRepository.findById(ticketRequest.getMotorbikeId())
                                       .orElseThrow(() -> new EntityNotFoundException(Motorbike.class, "motorbikeId",
                                                                                      ticketRequest.getMotorbikeId()
                                                                                                                                   .toString()));
        if (ticketRequest.getCustomerId() != null) {
            customer = customerRepository.findById(ticketRequest.getCustomerId())
                                         .orElseThrow(() -> new EntityNotFoundException(Customer.class, "customerId",
                                                                                        ticketRequest.getCustomerId()
                                                                                                                                   .toString()));
        }
        if (ticketRequest.getRepairingEmployeeId() != null) {
            repairingEmployee = employeeRepository.findById(ticketRequest.getRepairingEmployeeId())
                                                  .orElseThrow(() -> new EntityNotFoundException(Employee.class,
                                                                                                 "repairingEmployeeId"
                                                          , ticketRequest.getRepairingEmployeeId()
                                                                                                                                                     .toString()));
        }
        do {
            code = RandomCodeGenerator.genCode("HD", 5, false, true);
        } while (ticketRepository.existsByCode(code));
        ticket.setCode(code);
        ticket.setCustomer(customer);
        ticket.setMotorbike(motorbike);
        ticket.setRepairingEmployee(repairingEmployee);
        ticket.setNote(ticketRequest.getNote());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus(ticketRequest.getStatus());
        ticket.setDiscount(ticketRequest.getDiscount());
        ticket.setAppointmentDate(ticketRequest.getAppointmentDate());
        ticket.setPaymentMethod(ticketRequest.getPaymentMethod());
        return ticket;
    }
}
