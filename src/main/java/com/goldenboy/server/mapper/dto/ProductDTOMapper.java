package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.ProductDTO;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTOMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ModelDTOMapper modelDTOMapper;
    @Autowired
    private CategoryDTOMapper categoryDTOMapper;

    public ProductDTO toProductDTO(Product product) {
        TypeMap<Product, ProductDTO> propertyMapper = mapper.getTypeMap(Product.class, ProductDTO.class) ==
                                                      null ? mapper.createTypeMap(Product.class, ProductDTO.class) :
                mapper.getTypeMap(Product.class, ProductDTO.class);
        propertyMapper.addMappings(mapper -> {
            mapper.map(src -> src.getCategory().getName(), ProductDTO::setCategory);
            mapper.using(context -> categoryDTOMapper.toCategoryDTO((Category) context.getSource()))
                  .map(Product::getCategory, ProductDTO::setCategory);
        });
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);

        if (product.getModels() != null) productDTO.setModels(modelDTOMapper.toModelDTOs(product.getModels()));
        return mapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> toProductDTOs(List<Product> products) {
        return products.stream().map(this::toProductDTO).collect(Collectors.toList());
    }
}
