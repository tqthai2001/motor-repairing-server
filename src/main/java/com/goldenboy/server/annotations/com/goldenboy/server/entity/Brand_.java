package com.goldenboy.server.annotations.com.goldenboy.server.entity;

import com.goldenboy.server.annotations.com.goldenboy.server.entity.base.BaseEntity_;
import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Brand.class)
public abstract class Brand_ extends BaseEntity_ {
    public static final String MODELS = "models";
    public static final String BRAND_NAME = "brandName";
    public static volatile SetAttribute<Brand, Model> models;
    public static volatile SingularAttribute<Brand, String> brandName;
}

