package com.goldenboy.server.service;

import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;

import java.util.List;

public interface ModelService extends BaseService<Model> {
    List<Product> getAllProductsByModelsId(Long modelId);

    void deleteProductFromModel(Long productId, Long modelId);

    Product addProductToModel(Long productId, Long modelId);

    List<Model> searchModel(List<SearchCriteria> params);
}
