package com.customerCare.dtoMapper;

import com.customerCare.dto.ProductDto;
import com.customerCare.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDtoMapper {

    public static ProductDto productToProductDto (Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setOrderId(productDto.getOrderId());
        productDto.setId(product.getId());
        productDto.setType(productDto.getType());
        productDto.setAmount(productDto.getAmount());
        productDto.setPrice(productDto.getPrice());

        return productDto;
    }

    public static List<Product> productsDtoToProducts (List<ProductDto> productsDto) {

        return productsDto.stream()
                    .map(productDto -> {
                        Product product = new Product();
                        product.setAmount(productDto.getAmount());
                        product.setPrice(productDto.getPrice());
                        product.setType(productDto.getType());
                        return product;})
                    .collect(Collectors.toList());

    }

}
