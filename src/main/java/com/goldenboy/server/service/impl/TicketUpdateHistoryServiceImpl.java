package com.goldenboy.server.service.impl;

import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.entity.TicketUpdateHistory;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.repository.TicketRepository;
import com.goldenboy.server.repository.TicketUpdateHistoryRepository;
import com.goldenboy.server.service.TicketUpdateHistoryService;
import com.goldenboy.server.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketUpdateHistoryServiceImpl extends BaseServiceImpl<TicketUpdateHistory>
        implements TicketUpdateHistoryService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketUpdateHistoryRepository repository;

    protected TicketUpdateHistoryServiceImpl(TicketUpdateHistoryRepository ticketUpdateHistoryRepository) {
        super(ticketUpdateHistoryRepository);
    }

    @Override
    public List<TicketUpdateHistory> findByTicketId(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                                        .orElseThrow(() -> new EntityNotFoundException(Ticket.class, "id",
                                                                                       ticketId.toString()));
        return repository.findByTicketId(ticketId);
    }
}
