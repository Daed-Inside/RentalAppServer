package com.project.gcs18402.rentalz.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class PropertiesDto {
    private Long id;

    private Long userId;

    private String name;

    private String address;

    private String city;

    private String district;

    private String ward;

    private String type;

    private String furniture;

    private Integer bedroom;

    private Double price;

    private String reporter;

    private List<NoteDto> listNote;
}
