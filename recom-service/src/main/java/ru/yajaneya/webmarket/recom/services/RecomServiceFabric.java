package ru.yajaneya.webmarket.recom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecomServiceFabric {
    private final BuyProductsService buyProductsService;
    private final PutToCartProductsService putToCartProductsService;

    public RecomService getRecomeService (String type) {
        switch (type) {
            case "core":
                return buyProductsService;
            case "cart":
                return putToCartProductsService;
            default:
                return null;
        }
    }

}
