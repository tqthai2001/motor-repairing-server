package com.goldenboy.server.annotations.com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Service;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.entity.compositekey.TicketServiceId;
import com.goldenboy.server.entity.connectentity.TicketService;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TicketService.class)
public abstract class TicketService_ {
    public static final String TICKET = "ticket";
    public static final String SERVICE = "service";
    public static final String PRICE = "price";
    public static final String ID = "id";
    public static volatile SingularAttribute<TicketService, Ticket> ticket;
    public static volatile SingularAttribute<TicketService, Service> service;
    public static volatile SingularAttribute<TicketService, BigDecimal> price;
    public static volatile SingularAttribute<TicketService, TicketServiceId> id;
}

