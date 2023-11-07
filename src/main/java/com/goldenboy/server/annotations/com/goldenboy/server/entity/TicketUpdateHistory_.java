package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Ticket;
import com.goldenboy.server.entity.TicketUpdateHistory;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TicketUpdateHistory.class)
public abstract class TicketUpdateHistory_ extends BaseEntity_ {
    public static final String UPDATED_BY = "updatedBy";
    public static final String TICKET = "ticket";
    public static final String NEW_STATUS = "newStatus";
    public static final String OLD_STATUS = "oldStatus";
    public static final String UPDATED_DATE = "updatedDate";
    public static final String CONTENT = "content";
    public static volatile SingularAttribute<TicketUpdateHistory, Employee> updatedBy;
    public static volatile SingularAttribute<TicketUpdateHistory, Ticket> ticket;
    public static volatile SingularAttribute<TicketUpdateHistory, Byte> newStatus;
    public static volatile SingularAttribute<TicketUpdateHistory, Byte> oldStatus;
    public static volatile SingularAttribute<TicketUpdateHistory, LocalDateTime> updatedDate;
    public static volatile SingularAttribute<TicketUpdateHistory, String> content;
}

