package com.malpro.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 25.05.22.
 */

@Getter
@Setter
public class ProductFeatureNumericDto extends ProductFeatureDto {
    private String numericValue;
    private String etimUnitCode;
}
