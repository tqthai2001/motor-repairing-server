package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Brand;
import com.goldenboy.server.payload.crudrequest.BrandRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandRequestMapper {
    @Autowired
    private ModelMapper mapper;

    public Brand toBrand(BrandRequest brandRequest) {
        return mapper.map(brandRequest, Brand.class);
    }
}
