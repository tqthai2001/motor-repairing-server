package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Service;
import com.goldenboy.server.entity.connectentity.TicketService;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.payload.connectrequest.TicketServiceRequest;
import com.goldenboy.server.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketServiceRequestMapper {
    @Autowired
    private ServiceRepository serviceRepository;

    public TicketService toTicketService(TicketServiceRequest request) {
        TicketService ticketService = new TicketService();
        Service service = serviceRepository.findById(request.getServiceId())
                                           .orElseThrow(() -> new EntityNotFoundException(Service.class, "serviceId",
                                                                                          request.getServiceId()
                                                                                                                             .toString()));
        ticketService.setService(service);
        ticketService.setPrice(service.getPrice());
        return ticketService;
    }
}
