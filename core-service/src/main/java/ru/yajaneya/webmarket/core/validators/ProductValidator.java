package ru.yajaneya.webmarket.core.validators;

import org.springframework.stereotype.Component;
import ru.yajaneya.webmarket.api.core.ProductDto;
import ru.yajaneya.webmarket.core.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate (ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice().compareTo(BigDecimal.ONE) < 0) {
            errors.add("Цена продукта должна быть больше 0");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт должнен иметь название");
        }
        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
