package com.goldenboy.server.annotations.com.goldenboy.server.entity.compositekey;

import com.goldenboy.server.entity.compositekey.TicketProductId;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TicketProductId.class)
public abstract class TicketProductId_ {
    public static final String PRODUCT_ID = "productId";
    public static final String TICKET_ID = "ticketId";
    public static volatile SingularAttribute<TicketProductId, Long> productId;
    public static volatile SingularAttribute<TicketProductId, Long> ticketId;
}

