package ru.yajaneya.webmarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.yajaneya.webmarket.api.core.ProductDto;
import ru.yajaneya.webmarket.core.entities.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class ProductsServiceCache implements ProductsService{
    private final ProductsServiceImpl productsService;

    private Map<Long, Product> cache;

    @PostConstruct
    private void init() {
        cache = new HashMap<>();
    }

    @Override
    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, String categoryName, Integer page) {
        return productsService.findAll(minPrice, maxPrice, partTitle, categoryName, page);
    }

    @Override
    public Optional<Product> findByID(Long id) {
        if (cache.containsKey(id)) {
            return Optional.of(cache.get(id));
        } else {
            Optional<Product> product = productsService.findByID(id);
            product.ifPresent(value -> cache.put(value.getId(), value));
            return product;
        }
    }

    @Override
    public Product save(Product product) {
        return productsService.save(product);
    }

    @Override
    public Product update(ProductDto productDto) {
        Product product = productsService.update(productDto);
        Long id = product.getId();
        if (cache.containsKey(id)) {
            cache.put(id, product);
       }
        return product;
    }

    @Override
    public void deleteById(Long id) {
        if (cache.containsKey(id)) {
            cache.remove(id);
        }
        productsService.deleteById(id);
    }
}
