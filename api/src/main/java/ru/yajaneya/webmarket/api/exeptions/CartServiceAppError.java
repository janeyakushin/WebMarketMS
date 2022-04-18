package ru.yajaneya.webmarket.api.exeptions;

public class CartServiceAppError extends AppError {
    public enum CartServiceErrors {
        CART_IS_BROKEN, CART_ID_GENERATOR_DISABLED, CART_NOT_FOUND, CORE_SERVICE_INTEGRETION_ERROR
    }

    public CartServiceAppError() {
    }

    public CartServiceAppError(String code, String message) {
        super(code, message);
    }
}
