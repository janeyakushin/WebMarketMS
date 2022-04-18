package ru.yajaneya.webmarket.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yajaneya.webmarket.api.core.ProductDto;
import ru.yajaneya.webmarket.api.exeptions.ResourceNotFoundException;
import ru.yajaneya.webmarket.core.entities.Product;
import ru.yajaneya.webmarket.core.services.CategoriesService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoriesService categoriesService;

    public Product dtoToEntity (ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice(),
                categoriesService.findByCategoryName(productDto.getCategory())
                        .orElseThrow(() -> new ResourceNotFoundException
                                ("Категория " + productDto.getCategory() + " не найдена.")));
    }

    public ProductDto entityToDto (Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory().getCategoryName());
    }

}
