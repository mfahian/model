package com.malpro.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 25.05.22.
 */
@Getter
@Setter
public class ProductFeatureRangeDto extends ProductFeatureDto {
    private String lowerBound;
    private String upperBound;
    private String etimUnitCode;
}
