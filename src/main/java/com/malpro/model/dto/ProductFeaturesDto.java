package com.malpro.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by fahian on 03.06.22.
 */
@Getter
@Setter
@Builder
public class ProductFeaturesDto {
    private String etimGroup;
    private String referenceFeatureSystem;
    private Set<ProductFeatureDto> productFeature; //todo change to list, then mapper list->set
}
