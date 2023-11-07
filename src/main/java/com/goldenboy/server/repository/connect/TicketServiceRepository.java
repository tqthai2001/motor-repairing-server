package com.goldenboy.server.repository.connect;

import com.goldenboy.server.entity.compositekey.TicketServiceId;
import com.goldenboy.server.entity.connectentity.TicketService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketServiceRepository extends JpaRepository<TicketService, TicketServiceId> {}
