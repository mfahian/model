package com.malpro.model.dto.feature;

import com.malpro.model.model.EtimFeatureType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by fahian on 06.06.22.
 */
@Slf4j
public class ProductFeatureFactory {

    private ProductFeatureFactory() {
    }

    public static IFeatureFactory getFactory(EtimFeatureType type) {
        log.debug(type.toString());
        return switch (type) {
            case Alphanumeric -> new AlphanumericFeatureFactory();
            case Numeric -> new NumericFeatureFactory();
            case Logical -> new LogicalFeatureFactory();
            case Range -> new RangeFeatureFactory();
        };
    }
}
