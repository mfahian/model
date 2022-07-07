package com.malpro.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by fahian on 22.05.22.
 */
@Getter
@Setter
public class ProductDto {
    private String code;
    private String shortDescription;
    private String longDescription;
    private String globalTradeItemNumber;
    private String manufacturerCode;
    private String manufacturerName;
    private List<ProductFeaturesDto> productFeatures;
    private ProductOrderDetailsDto productOrderDetails;
}
