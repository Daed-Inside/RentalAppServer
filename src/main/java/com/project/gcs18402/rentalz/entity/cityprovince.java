package com.project.gcs18402.rentalz.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tinhthanhpho")
public class cityprovince {

    @Id
    @Column(name = "matp")
    private String cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
