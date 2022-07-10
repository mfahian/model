package com.malpro.model.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "CLASS_FEATURE_ID", referencedColumnName = "ID")
    @JsonIgnore
    private EtimClassFeature classFeatureCode;

    @ManyToOne
    @JoinColumn(name = "VALUE_CODE", referencedColumnName = "CODE")
    private EtimValue value;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
}
