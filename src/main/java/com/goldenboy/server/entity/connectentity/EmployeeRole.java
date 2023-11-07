package com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Role;
import com.goldenboy.server.entity.compositekey.EmployeeRoleId;

import javax.persistence.*;

@Deprecated
@Entity
@Table(name = "employees_roles")
public class EmployeeRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private EmployeeRoleId id;
    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public EmployeeRoleId getId() {
        return id;
    }

    public void setId(EmployeeRoleId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
