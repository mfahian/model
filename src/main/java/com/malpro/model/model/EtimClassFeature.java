package com.malpro.model.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String id;

    @ManyToOne
    @JoinColumn(name = "CLASS_CODE", referencedColumnName = "CODE")
    @JsonIgnore
    private EtimClass classCode;

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
