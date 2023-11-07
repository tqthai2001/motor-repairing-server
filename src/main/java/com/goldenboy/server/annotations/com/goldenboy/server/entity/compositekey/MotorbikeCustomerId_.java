package com.goldenboy.server.annotations.com.goldenboy.server.entity.compositekey;

import com.goldenboy.server.entity.compositekey.MotorbikeCustomerId;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotorbikeCustomerId.class)
public abstract class MotorbikeCustomerId_ {
    public static final String CUSTOMER_ID = "customerId";
    public static final String MOTORBIKE_ID = "motorbikeId";
    public static volatile SingularAttribute<MotorbikeCustomerId, Long> customerId;
    public static volatile SingularAttribute<MotorbikeCustomerId, Long> motorbikeId;
}

