package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.payload.crudrequest.ModelRequest;
import com.goldenboy.server.repository.BrandRepository;
import com.goldenboy.server.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelRequestMapper {
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandRepository brandRepository;

    public Model toModel(ModelRequest modelRequest) {
        Brand brand;
        Model newModel = new Model();
        if (!brandRepository.existsByBrandName(modelRequest.getBrandName())) {
            brand = new Brand();
            brand.setBrandName(modelRequest.getBrandName());
            brandService.save(brand);
        } else {
            brand = brandRepository.findByBrandName(modelRequest.getBrandName()).get();
        }
        newModel.setModelName(modelRequest.getModelName());
        newModel.setBrand(brand);
        return newModel;
    }
}
