package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.entity.Product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Model.class)
public abstract class Model_ extends BaseEntity_ {
    public static final String MODEL_NAME = "modelName";
    public static final String MOTORBIKES = "motorbikes";
    public static final String BRAND = "brand";
    public static final String PRODUCTS = "products";
    public static volatile SingularAttribute<Model, String> modelName;
    public static volatile SetAttribute<Model, Motorbike> motorbikes;
    public static volatile SingularAttribute<Model, Brand> brand;
    public static volatile SetAttribute<Model, Product> products;
}

