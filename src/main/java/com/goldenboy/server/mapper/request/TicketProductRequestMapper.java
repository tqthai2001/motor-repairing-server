package com.goldenboy.server.mapper.request;

import com.goldenboy.server.entity.Product;
import com.goldenboy.server.entity.connectentity.TicketProduct;
import com.goldenboy.server.exception.EntityNotFoundException;
import com.goldenboy.server.payload.connectrequest.TicketProductRequest;
import com.goldenboy.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketProductRequestMapper {
    @Autowired
    private ProductRepository productRepository;

    public TicketProduct toTicketProduct(TicketProductRequest request) {
        TicketProduct ticketProduct = new TicketProduct();
        Product product = productRepository.findById(request.getProductId())
                                           .orElseThrow(() -> new EntityNotFoundException(Product.class, "productId",
                                                                                          request.getProductId()
                                                                                                                             .toString()));
        ticketProduct.setProduct(product);
        ticketProduct.setQuantity(request.getQuantity());
        ticketProduct.setPrice(product.getPrice());
        return ticketProduct;
    }
}
