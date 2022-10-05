package com.malpro.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fahian on 03.06.22.
 */
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductFeatureAlphanumericDto.class, name = "alphanumeric"),
        @JsonSubTypes.Type(value = ProductFeatureRangeDto.class, name = "range"),
        @JsonSubTypes.Type(value = ProductFeatureNumericDto.class, name = "numeric"),
        @JsonSubTypes.Type(value = ProductFeatureLogicalDto.class, name = "logical")
})
public abstract class ProductFeatureDto {
    private String etimFeatureCode;
}
