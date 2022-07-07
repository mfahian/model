package com.malpro.model.model;

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

    @OneToMany(mappedBy = "classCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EtimClassFeature> features;

}
