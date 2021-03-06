package ru.yajaneya.webmarket.core.converters;

import org.springframework.stereotype.Component;
import ru.yajaneya.webmarket.api.core.OrderItemDto;
import ru.yajaneya.webmarket.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getTitle(),
                orderItem.getQuantity(),
                orderItem.getPricePerProduct(),
                orderItem.getPrice()
        );
    }
}
