package com.malpro.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Created by martin.fahian on 06.02.21.
 */
@Entity
@Table(name = "ETIM_GROUP")
@Getter
@Setter
@ToString
public class EtimGroup {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column (name = "DESCRIPTION")
    private String description;

}
