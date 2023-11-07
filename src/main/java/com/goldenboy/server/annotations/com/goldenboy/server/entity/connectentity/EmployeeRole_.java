package com.goldenboy.server.annotations.com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Role;
import com.goldenboy.server.entity.compositekey.EmployeeRoleId;
import com.goldenboy.server.entity.connectentity.EmployeeRole;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeRole.class)
public abstract class EmployeeRole_ {
    public static final String ROLE = "role";
    public static final String ID = "id";
    public static final String EMPLOYEE = "employee";
    public static volatile SingularAttribute<EmployeeRole, Role> role;
    public static volatile SingularAttribute<EmployeeRole, EmployeeRoleId> id;
    public static volatile SingularAttribute<EmployeeRole, Employee> employee;
}

