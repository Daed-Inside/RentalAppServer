package com.project.gcs18402.rentalz.request;

import lombok.Data;

@Data
public class PropertiesRequest {
    private Long id;
    private Long userId;
    private String name;
    private String address;
    private String city;
    private String district;
    private String ward;
    private String type;
    private String furniture;
    private String bedroom;
    private String price;
    private String reporter;
    private String note;
}
