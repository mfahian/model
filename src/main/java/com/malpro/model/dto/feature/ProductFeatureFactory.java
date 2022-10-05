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
            case Alphanumeric -> new AlphanumericFeatureFactory();
            case Numeric -> new NumericFeatureFactory();
            case Logical -> new LogicalFeatureFactory();
            case Range -> new RangeFeatureFactory();
        };
    }
}
