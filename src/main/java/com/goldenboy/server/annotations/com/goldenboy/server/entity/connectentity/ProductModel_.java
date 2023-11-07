package com.goldenboy.server.annotations.com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.entity.compositekey.ProductModelId;
import com.goldenboy.server.entity.connectentity.ProductModel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductModel.class)
public abstract class ProductModel_ {
    public static final String PRODUCT = "product";
    public static final String MODEL = "model";
    public static final String ID = "id";
    public static volatile SingularAttribute<ProductModel, Product> product;
    public static volatile SingularAttribute<ProductModel, Model> model;
    public static volatile SingularAttribute<ProductModel, ProductModelId> id;
}

