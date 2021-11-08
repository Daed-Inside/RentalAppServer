package com.project.gcs18402.rentalz.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "quanhuyen")
public class district {
    @Id
    @Column(name = "maqh")
    private String districtId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "matp")
    private String cityId;
}
