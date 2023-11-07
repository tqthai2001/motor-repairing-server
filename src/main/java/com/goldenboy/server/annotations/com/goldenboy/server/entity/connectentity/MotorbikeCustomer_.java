package com.goldenboy.server.annotations.com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Customer;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.compositekey.MotorbikeCustomerId;
import com.goldenboy.server.entity.connectentity.MotorbikeCustomer;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotorbikeCustomer.class)
public abstract class MotorbikeCustomer_ {
    public static final String MOTORBIKE = "motorbike";
    public static final String ID = "id";
    public static final String CUSTOMER = "customer";
    public static volatile SingularAttribute<MotorbikeCustomer, Motorbike> motorbike;
    public static volatile SingularAttribute<MotorbikeCustomer, MotorbikeCustomerId> id;
    public static volatile SingularAttribute<MotorbikeCustomer, Customer> customer;
}

