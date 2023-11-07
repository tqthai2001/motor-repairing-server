package com.goldenboy.server.service;

import com.goldenboy.server.entity.Product;
import com.goldenboy.server.payload.searchrequest.SearchCriteria;
import com.goldenboy.server.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    // DELETE
    @Transactional
    void deleteByIdTmp(Long productId);

    @Transactional
    void deleteByIdArrayTmp(List<Long> productIds);

    List<Product> searchProduct(List<SearchCriteria> params);
}
