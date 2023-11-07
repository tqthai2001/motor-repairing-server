package com.goldenboy.server.service;

import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.payload.crudrequest.TicketRequest;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface TicketService extends BaseService<Ticket> {
    Ticket save(TicketRequest ticketRequest);

    Ticket updateById(Long id, TicketRequest newTicketRequest);

    @Transactional
    void deleteByIdTmp(Long id);

    // FILTER
    List<Ticket> searchTicket(List<SearchCriteria> params);

    Map<String, Object> searchTicketPaging(List<SearchCriteria> params, int page, int size);
}
