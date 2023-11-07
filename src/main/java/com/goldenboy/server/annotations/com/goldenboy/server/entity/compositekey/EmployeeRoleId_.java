package com.goldenboy.server.annotations.com.goldenboy.server.entity.compositekey;

import com.goldenboy.server.entity.compositekey.EmployeeRoleId;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeRoleId.class)
public abstract class EmployeeRoleId_ {
    public static final String ROLE_ID = "roleId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static volatile SingularAttribute<EmployeeRoleId, Long> roleId;
    public static volatile SingularAttribute<EmployeeRoleId, Long> employeeId;
}

