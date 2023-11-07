package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.entity.connectentity.TicketProduct;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends BaseEntity_ {
    public static final String IMAGE = "image";
    public static final String MODELS = "models";
    public static final String CODE = "code";
    public static final String QUANTITY = "quantity";
    public static final String DESCRIPTION = "description";
    public static final String ACTIVE = "active";
    public static final String UPDATED_DATE = "updatedDate";
    public static final String UNIT = "unit";
    public static final String CREATED_DATE = "createdDate";
    public static final String PRICE = "price";
    public static final String NAME = "name";
    public static final String TICKET_PRODUCTS = "ticketProducts";
    public static final String CATEGORY = "category";
    public static volatile SingularAttribute<Product, String> image;
    public static volatile SetAttribute<Product, Model> models;
    public static volatile SingularAttribute<Product, String> code;
    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Boolean> active;
    public static volatile SingularAttribute<Product, LocalDateTime> updatedDate;
    public static volatile SingularAttribute<Product, String> unit;
    public static volatile SingularAttribute<Product, LocalDateTime> createdDate;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SetAttribute<Product, TicketProduct> ticketProducts;
    public static volatile SingularAttribute<Product, Category> category;
}

