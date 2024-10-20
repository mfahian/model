package com.malpro.model.dto;

import com.malpro.model.dto.feature.AlphanumericFeatureFactory;
import com.malpro.model.dto.feature.IFeatureFactory;
import com.malpro.model.dto.feature.LogicalFeatureFactory;
import com.malpro.model.dto.feature.NumericFeatureFactory;
import com.malpro.model.dto.feature.ProductFeatureFactory;
import com.malpro.model.dto.feature.RangeFeatureFactory;
import com.malpro.model.model.EtimFeatureType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 26.06.22.
 */
class ProductFeatureFactoryTest {

    @Test
    @DisplayName("Create alphanumeric factory test")
    void createAlphanumericFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.ALPHANUMERIC);

        assertThat(factory.getClass(), Matchers.is(AlphanumericFeatureFactory.class));
    }

    @Test
    @DisplayName("Create numeric factory test")
    void createNumericFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.NUMERIC);

        assertThat(factory.getClass(), Matchers.is(NumericFeatureFactory.class));
    }

    @Test
    @DisplayName("Create logical factory test")
    void createLogicalFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.LOGICAL);

        assertThat(factory.getClass(), Matchers.is(LogicalFeatureFactory.class));
    }

    @Test
    @DisplayName("Create range factory test")
    void createRangeFactoryTest() {

        final IFeatureFactory factory = ProductFeatureFactory.getFactory(EtimFeatureType.RANGE);

        assertThat(factory.getClass(), Matchers.is(RangeFeatureFactory.class));
    }
}