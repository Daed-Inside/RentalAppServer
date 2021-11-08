package com.project.gcs18402.rentalz.controller;

import com.project.gcs18402.rentalz.dto.CityDto;
import com.project.gcs18402.rentalz.dto.DistrictDto;
import com.project.gcs18402.rentalz.dto.PropertiesDto;
import com.project.gcs18402.rentalz.dto.WardDto;
import com.project.gcs18402.rentalz.manager.LocationManager;
import com.project.gcs18402.rentalz.request.LocationRequest;
import com.project.gcs18402.rentalz.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/location")
public class LocationController {
    @Autowired
    ResponseUtils responseUtils;
    @Autowired
    LocationManager locationManager;

    @GetMapping("/getAllCity")
    public ResponseEntity<?> getallcity() {
        try {
            List<CityDto> result = locationManager.getAllCity();
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getDistrict")
    public ResponseEntity<?> getDistrict(@RequestBody LocationRequest req) {
        try {
            List<DistrictDto> result = locationManager.getDistrictByCity(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getWard")
    public ResponseEntity<?> getWard(@RequestBody LocationRequest req) {
        try {
            List<WardDto> result = locationManager.getWardByDistrict(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
