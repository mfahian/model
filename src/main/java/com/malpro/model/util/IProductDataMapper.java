package com.malpro.model.util;


import com.malpro.model.dto.ProductDataDto;
import com.malpro.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by fahian on 22.05.22.
 */
@Mapper(componentModel = "spring")
public interface IProductDataMapper {

    @Mapping(source = "productCode", target = "code")
    @Mapping(source = "ean", target = "globalTradeItemNumber")
    @Mapping(source = "unit", target = "productOrderDetails.orderUnit")
    @Mapping(source = "unit", target = "productOrderDetails.contentUnit")
    @Mapping(source = "priceQuantity", target = "productOrderDetails.priceQuantity")
    @Mapping(ignore = true, target = "productFeatures")
    @Mapping(ignore = true, target = "manufacturerCode")
    @Mapping(ignore = true, target = "manufacturerName")
    ProductDto toProductDto(ProductDataDto productDataDto);

}
