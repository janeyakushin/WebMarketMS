package ru.yajaneya.webmarket.api.exeptions;

public class CoreServiceAppError extends AppError {
    public enum CoreServiceErrors {
        PRODUCT_NOT_FOUND, CART_SERVICE_INTEGRATION_ERROR
    }

    public CoreServiceAppError() {
    }

    public CoreServiceAppError(String code, String message) {
        super(code, message);
    }
}
