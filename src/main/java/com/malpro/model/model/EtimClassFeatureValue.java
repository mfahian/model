package com.malpro.model.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Created by martin.fahian on 06.02.21.
 */
@Entity
@Table(name = "ETIM_CLASS_FEATURE_VALUE")
@Getter
@Setter
public class EtimClassFeatureValue {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_FEATURE_ID", referencedColumnName = "ID")
    // @JsonIgnore
    private EtimClassFeature classFeatureCode;

    @ManyToOne
    @JoinColumn(name = "VALUE_CODE", referencedColumnName = "CODE")
    private EtimValue value;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
}
