package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.entity.Product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ extends BaseEntity_ {
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRODUCTS = "products";
    public static volatile SingularAttribute<Category, String> code;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, String> description;
    public static volatile SetAttribute<Category, Product> products;
}

