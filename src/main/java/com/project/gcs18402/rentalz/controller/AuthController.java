package com.project.gcs18402.rentalz.controller;

import com.project.gcs18402.rentalz.dto.CityDto;
import com.project.gcs18402.rentalz.dto.DistrictDto;
import com.project.gcs18402.rentalz.dto.PropertiesDto;
import com.project.gcs18402.rentalz.dto.WardDto;
import com.project.gcs18402.rentalz.manager.AuthManager;
import com.project.gcs18402.rentalz.manager.LocationManager;
import com.project.gcs18402.rentalz.request.LocationRequest;
import com.project.gcs18402.rentalz.request.LoginRequest;
import com.project.gcs18402.rentalz.request.PropertiesRequest;
import com.project.gcs18402.rentalz.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    ResponseUtils responseUtils;
    @Autowired
    AuthManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Map<String, Object> result = authManager.Login(req);
            if (result.get("message").toString().equals("success")) {
                return responseUtils.getResponseEntity(result.get("data"), 1, "success", HttpStatus.OK);
            } else if (result.get("message").toString().equals("exist")) {
                return responseUtils.getResponseEntity(null, -2, "Account does not exist", HttpStatus.BAD_REQUEST);
            } else if (result.get("message").toString().equals("password")) {
                return responseUtils.getResponseEntity(null, -3, "Wrong password", HttpStatus.BAD_REQUEST);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody LoginRequest req) {
        try {
            String result = authManager.Signup(req);
            if (result.equals("success")) {
                return responseUtils.getResponseEntity(null, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -2, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
