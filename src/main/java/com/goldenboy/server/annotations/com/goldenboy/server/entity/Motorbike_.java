package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Ticket;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Motorbike.class)
public abstract class Motorbike_ extends BaseEntity_ {
    public static final String TICKETS = "tickets";
    public static final String LICENSE_PLATES = "licensePlates";
    public static final String MODEL = "model";
    public static final String CUSTOMERS = "customers";
    public static volatile SetAttribute<Motorbike, Ticket> tickets;
    public static volatile SingularAttribute<Motorbike, String> licensePlates;
    public static volatile SingularAttribute<Motorbike, Model> model;
    public static volatile SetAttribute<Motorbike, Customer> customers;
}

