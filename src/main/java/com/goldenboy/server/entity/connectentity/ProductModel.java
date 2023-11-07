package com.goldenboy.server.entity.connectentity;

import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.entity.compositekey.ProductModelId;

import javax.persistence.*;

@Deprecated
@Entity
@Table(name = "products_models")
public class ProductModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private ProductModelId id;
    @MapsId("modelId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductModelId getId() {
        return id;
    }

    public void setId(ProductModelId id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
