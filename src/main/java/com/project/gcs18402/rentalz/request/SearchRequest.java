package com.project.gcs18402.rentalz.request;

import lombok.Data;

@Data
public class SearchRequest {
    private String searchKey;
    private String name;
    private String fromPrice;
    private String toPrice;
    private String city;
    private String district;
    private String ward;
    private String bedroom;
    private String reporter;
    private String type;
    private String furniture;
    private String email;
    private Long userId;
}
