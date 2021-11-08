package com.project.gcs18402.rentalz.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String address;
    private String phone;
}
