package com.project.gcs18402.rentalz.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "xaphuongthitran")
public class ward {
    @Id
    @Column(name = "xaid")
    private String wardId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "maqh")
    private String districtId;
}
