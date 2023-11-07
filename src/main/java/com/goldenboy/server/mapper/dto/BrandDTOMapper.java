package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.BrandDTO;
import com.goldenboy.server.entity.Brand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandDTOMapper {
    @Autowired
    private ModelMapper mapper;

    public BrandDTO toBrandDTO(Brand brand) {
        return mapper.map(brand, BrandDTO.class);
    }

    public List<BrandDTO> toBrandDTOs(List<Brand> brands) {
        return brands.stream().map(this::toBrandDTO).collect(Collectors.toList());
    }
}
