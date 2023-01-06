package com.malpro.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Created by martin.fahian on 06.02.21.
 */
@Entity
@Table(name = "ETIM_FEATURE")
@Getter
@Setter
@ToString
public class EtimFeature {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "TYPE")
    @Enumerated (EnumType.STRING)
    private EtimFeatureType type;

    @Column(name = "DESCRIPTION")
    private String description;

}
