package ru.yajaneya.webmarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.yajaneya.webmarket.api.core.ProductDto;
import ru.yajaneya.webmarket.api.exeptions.ResourceNotFoundException;
import ru.yajaneya.webmarket.core.entities.Product;
import ru.yajaneya.webmarket.core.repositories.ProductsRepository;
import ru.yajaneya.webmarket.core.repositories.specifications.ProductsSpecifications;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService{

    private final ProductsRepository productsRepository;

    public Page<Product> findAll(
            Integer minPrice,
            Integer maxPrice,
            String partTitle,
            String categoryName,
            Integer page) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        if (categoryName != null) {
            if (!categoryName.equals("")) {
                spec = spec.and(ProductsSpecifications.categoryEqual(categoryName));
            }
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));

    }

    public Optional<Product> findByID (Long id) {
        return productsRepository.findById(id);
    }

    public Product save (Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update (ProductDto productDto) {
        Long id = productDto.getId();
        Product product = findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("???????????????????? ????????????????. ?????????????? ?? id = " + id + " ???? ????????????."));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }


    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

}
