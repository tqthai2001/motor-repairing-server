package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.common.StatusConverter;
import com.goldenboy.server.dto.TicketDTO;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketDTOMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MotorbikeDTOMapper motorbikeDTOMapper;
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private EmployeeDTOMapper employeeDTOMapper;
    @Autowired
    private TicketServiceDTOMapper ticketServiceDTOMapper;
    @Autowired
    private TicketProductDTOMapper ticketProductDTOMapper;

    public TicketDTO toTicketDTO(Ticket ticket) {
        TypeMap<Ticket, TicketDTO> propertyMapper = modelMapper.getTypeMap(Ticket.class, TicketDTO.class) ==
                                                    null ? modelMapper.createTypeMap(Ticket.class, TicketDTO.class) :
                modelMapper.getTypeMap(Ticket.class, TicketDTO.class);
        Converter<Byte, String> statusConverter = context -> {
            Byte status = context.getSource();
            return StatusConverter.toStatusString(status);
        };
        propertyMapper.addMappings(mapper -> {
            mapper.using(statusConverter).map(Ticket::getStatus, TicketDTO::setStatus);
            mapper.using(context -> motorbikeDTOMapper.toMotorbikeDTO((Motorbike) context.getSource()))
                  .map(Ticket::getMotorbike, TicketDTO::setMotorbike);
            mapper.using(context -> context.getSource() ==
                                    null ? null : customerDTOMapper.toCustomerDTO((Customer) context.getSource()))
                  .map(Ticket::getCustomer, TicketDTO::setCustomer);
            mapper.using(context -> context.getSource() ==
                                    null ? null : employeeDTOMapper.toEmployeeDTO((Employee) context.getSource()))
                  .map(Ticket::getRepairingEmployee, TicketDTO::setRepairingEmployee);
        });
        TicketDTO res = this.modelMapper.map(ticket, TicketDTO.class);
        if (ticket.getTicketsProducts() != null) {
            res.setProducts(ticketProductDTOMapper.toTicketProductDTOs(ticket.getTicketsProducts()));
        }
        if (ticket.getTicketsServices() != null) {
            res.setServices(ticketServiceDTOMapper.toTicketServiceDTOs(ticket.getTicketsServices()));
        }
        return res;
    }

    public List<TicketDTO> toTicketDTOs(List<Ticket> tickets) {
        return tickets.stream().map(this::toTicketDTO).collect(Collectors.toList());
    }
}
