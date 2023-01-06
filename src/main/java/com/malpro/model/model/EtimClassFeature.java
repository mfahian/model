package com.malpro.model.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Created by martin.fahian on 06.02.21.
 */
@Entity
@Table(name = "ETIM_CLASS_FEATURE")
@Getter
@Setter
public class EtimClassFeature {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLASS_CODE", referencedColumnName = "CODE")
    @JsonIgnore
    private EtimClass etimClass;

    @OneToMany(mappedBy = "classFeatureCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EtimClassFeatureValue> values;

    @ManyToOne
    @JoinColumn(name = "FEATURE_CODE", referencedColumnName = "CODE")
    private EtimFeature feature;

    @ManyToOne
    @JoinColumn(name = "UNIT_CODE", referencedColumnName = "CODE")
    private EtimUnit unitOfMeasure;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
}
