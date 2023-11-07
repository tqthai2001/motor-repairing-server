package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.common.ERole;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Role;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends BaseEntity_ {
    public static final String NAME = "name";
    public static final String EMPLOYEES = "employees";
    public static volatile SingularAttribute<Role, ERole> name;
    public static volatile SetAttribute<Role, Employee> employees;
}

