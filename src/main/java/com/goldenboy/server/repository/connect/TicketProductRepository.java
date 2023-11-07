package com.goldenboy.server.repository.connect;

import com.goldenboy.server.entity.compositekey.TicketProductId;
import com.goldenboy.server.entity.connectentity.TicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketProductRepository extends JpaRepository<TicketProduct, TicketProductId> {}
