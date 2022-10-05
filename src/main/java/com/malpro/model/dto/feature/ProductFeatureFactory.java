package com.malpro.model.dto.feature;

import com.malpro.model.model.EtimFeatureType;

/**
 * Created by fahian on 06.06.22.
 */
public class ProductFeatureFactory {

    private ProductFeatureFactory() {
    }

    public static IFeatureFactory getFactory(EtimFeatureType type) {
        return switch (type) {
            case ALPHANUMERIC -> new AlphanumericFeatureFactory();
            case NUMERIC -> new NumericFeatureFactory();
            case LOGICAL -> new LogicalFeatureFactory();
            case RANGE -> new RangeFeatureFactory();
        };
    }
}
