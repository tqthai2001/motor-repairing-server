package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Motorbike;
import com.goldenboy.server.payload.crudrequest.MotorbikeRequest;
import com.goldenboy.server.repository.BrandRepository;
import com.goldenboy.server.repository.ModelRepository;
import com.goldenboy.server.service.BrandService;
import com.goldenboy.server.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotorbikeRequestMapper {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ModelService modelService;

    public Motorbike toMotorbike(MotorbikeRequest motorbikeRequest) {
        Brand brand;
        Model model;
        Motorbike newMotorbike = new Motorbike();
        if (!brandRepository.existsByBrandName(motorbikeRequest.getBrandName())) {
            brand = new Brand();
            brand.setBrandName(motorbikeRequest.getBrandName());
            brandService.save(brand);
        } else {
            brand = brandRepository.findByBrandName(motorbikeRequest.getBrandName()).get();
        }
        if (!modelRepository.existsByModelNameAndBrandId(motorbikeRequest.getModelName(), brand.getId())) {
            model = new Model();
            model.setBrand(brand);
            model.setModelName(motorbikeRequest.getModelName());
            modelService.save(model);
        } else {
            model = modelRepository.findByModelNameAndBrandId(motorbikeRequest.getModelName(), brand.getId()).get();
        }
        newMotorbike.setModel(model);
        newMotorbike.setLicensePlates(motorbikeRequest.getLicensePlates());
        return newMotorbike;
    }
}
