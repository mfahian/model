package com.malpro.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by fahian on 29.05.22.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFeaturesTextDto {
    private String etimClass;
    private ReferenceFeatureSystem referenceFeatureSystem;
    private Map<String, String> featuresMap;
}
