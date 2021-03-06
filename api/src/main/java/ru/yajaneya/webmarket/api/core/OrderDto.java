package ru.yajaneya.webmarket.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель заказа")
public class OrderDto {

    @Schema(description = "ID заказа", required = true, example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", required = true, maxLength = 255, minLength = 3, example = "Vasiliy")
    private String username;
    @Schema(description = "Список продуктов заказа", required = true)
    private List<OrderItemDto> items;
    @Schema(description = "Итоговая сумма заказа", required = true, example = "360.00")
    private BigDecimal totalPrice;
    @Schema(description = "Адрес доставки", maxLength = 255, minLength = 10, example = "RU // 685000 // Магаданская область // г.Магадан // ул.Пролетарская // д.23")
    private String address;
    @Schema(description = "Адрес доставки для отображения на фронте", maxLength = 255, minLength = 10, example = "685000, Россия, г.Магадан, ул.Пролетарская, 23")
    private String addressToFront;
    @Schema(description = "Телефон для связи", maxLength = 25, minLength = 10, example = "+7413-225-11-81")
    private String phone;
    @Schema(description = "Статус заказа: CREATED, PAID, CANCELED", maxLength = 8, minLength = 4, example = "CREATED")
    private String status;


    public OrderDto() {
    }

    public OrderDto(Long id, String username, List<OrderItemDto> items, BigDecimal totalPrice, String address, String addressToFront, String phone, String status) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.addressToFront = addressToFront;
        this.phone = phone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressToFront() {
        return addressToFront;
    }

    public void setAddressToFront(String addressToFront) {
        this.addressToFront = addressToFront;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
