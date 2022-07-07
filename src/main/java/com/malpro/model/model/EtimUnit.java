package com.malpro.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by martin.fahian on 06.02.21.
 */
@Entity
@Table(name = "ETIM_UNIT")
@Getter
@Setter
@ToString
public class EtimUnit {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ABBREVIATION")
    private String abbreviation;

}
