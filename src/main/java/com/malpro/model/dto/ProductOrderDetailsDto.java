package com.malpro.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 22.05.22.
 */
@Getter
@Setter
public class ProductOrderDetailsDto {
    private String orderUnit;
    private String contentUnit;
    private Float priceQuantity;
}
