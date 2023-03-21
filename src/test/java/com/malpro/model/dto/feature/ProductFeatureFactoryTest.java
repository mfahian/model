package com.malpro.model.dto.feature;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.malpro.model.model.EtimFeatureType;

/**
 * Created by fahian on 26.06.22.
 */
class ProductFeatureFactoryTest {

    @Test
    @DisplayName("Create alphanumeric factory test")
    void createAlphanumericFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.Alphanumeric);

        assertThat(factory.getClass(), Matchers.is(AlphanumericFeatureFactory.class));
    }

    @Test
    @DisplayName("Create numeric factory test")
    void createNumericFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.Numeric);

        assertThat(factory.getClass(), Matchers.is(NumericFeatureFactory.class));
    }

    @Test
    @DisplayName("Create logical factory test")
    void createLogicalFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.Logical);

        assertThat(factory.getClass(), Matchers.is(LogicalFeatureFactory.class));
    }

    @Test
    @DisplayName("Create range factory test")
    void createRangeFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.Range);

        assertThat(factory.getClass(), Matchers.is(RangeFeatureFactory.class));
    }
}