package com.goldenboy.server.service;

import com.goldenboy.server.entity.TicketUpdateHistory;
import com.goldenboy.server.service.base.BaseService;

import java.util.List;

public interface TicketUpdateHistoryService extends BaseService<TicketUpdateHistory> {
    List<TicketUpdateHistory> findByTicketId(Long ticketId);
}
