package com.malpro.model.dto;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fahian on 24.09.22.
 */
class ReferenceFeatureSystemTest {

    @Test
    @DisplayName("Value of label test")
    void valueOfLabelTest() {

        final var referenceFeatureSystem = ReferenceFeatureSystem.valueOfLabel("ETIM-8.0");

        assertThat(referenceFeatureSystem, Matchers.is(ReferenceFeatureSystem.ETIM_8_0));
    }
}