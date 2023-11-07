package com.goldenboy.server.repository;

import com.goldenboy.server.entity.TicketUpdateHistory;
import com.goldenboy.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketUpdateHistoryRepository extends BaseRepository<TicketUpdateHistory, Long> {
    List<TicketUpdateHistory> findByTicketId(Long ticketId);
}
