package com.malpro.model.model;

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
@Table(name = "ETIM_CLASS")
@Getter
@Setter
public class EtimClass {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "GROUP_CODE", referencedColumnName = "CODE")
    private EtimGroup group;

    @OneToMany(mappedBy = "etimClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EtimClassFeature> features;

}
