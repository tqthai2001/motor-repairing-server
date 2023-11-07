package com.goldenboy.server.entity;

import com.goldenboy.server.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    @Column(name = "code", unique = true, nullable = false, length = 20)
    private String code;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "phone", length = 25, unique = true)
    private String phone;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "working_status")
    private Boolean workingStatus;
    @Column(name = "available")
    private Boolean available = true;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "employees_roles", joinColumns = {@JoinColumn(name = "employee_id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;
    @Size(max = 120)
    @Column(name = "password", length = 120)
    private String password;
    @OneToMany(mappedBy = "updatedBy", cascade = javax.persistence.CascadeType.PERSIST, orphanRemoval = true)
    private Set<TicketUpdateHistory> histories = new HashSet<>();
}
