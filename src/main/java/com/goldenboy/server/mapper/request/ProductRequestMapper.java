package com.goldenboy.server.mapper.request;

import com.goldenboy.server.common.RandomCodeGenerator;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.entity.Model;
import com.goldenboy.server.entity.Product;
import com.goldenboy.server.payload.crudrequest.ProductRequest;
import com.goldenboy.server.repository.ProductRepository;
import com.goldenboy.server.service.CategoryService;
import com.goldenboy.server.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelService modelService;

    public Product toProduct(ProductRequest productRequest) {
        String code;
        Product product = new Product();
        Category category;
        do {
            code = RandomCodeGenerator.genCode("SP", 3, false, true);
        } while (productRepository.existsByCode(code));
        product.setCode(code);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setUnit(productRequest.getUnit());
        product.setImage(productRequest.getImage() ==
                         null ? "https://vnpi-hcm.vn/wp-content/uploads/2018/01/no-image-800x600.png" :
                                 productRequest.getImage());
        category = categoryService.findById(productRequest.getCategoryId());
        product.setCategory(category);
        if (productRequest.getModelIdSet() != null) {
            for (var modelId : productRequest.getModelIdSet()) {
                Model model = modelService.findById(modelId);
                product.getModels().add(model);
            }
        }
        return product;
    }
}
