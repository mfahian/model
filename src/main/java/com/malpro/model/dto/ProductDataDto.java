package com.malpro.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by fahian on 29.05.22.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDataDto {
    private String productCode;

    private String etimClass;

    private String ean;

    private String shortDescription;

    private String longDescription;

    private Map<String, String> featuresMap;

    private String unit;

    private BigDecimal priceQuantity;
//
//    private String mimeType;
//
//    private String mimeSource;
//
//    private String mimeDescription;
}
