package ru.yajaneya.webmarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.webmarket.core.entities.OrderItem;
import ru.yajaneya.webmarket.core.repositories.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll () {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> findByID (Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem save (OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
