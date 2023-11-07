package com.goldenboy.server.entity;

import com.goldenboy.server.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "motorbikes")
public class Motorbike extends BaseEntity {
    @Column(name = "license_plates", nullable = false, length = 20)
    private String licensePlates;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "motorbikes_customers", joinColumns = {@JoinColumn(name = "motorbike_id")},
               inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private Set<Customer> customers = new HashSet<>();
    @OneToMany(mappedBy = "motorbike", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
