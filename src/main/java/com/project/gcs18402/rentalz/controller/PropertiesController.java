package com.project.gcs18402.rentalz.controller;

import com.project.gcs18402.rentalz.dto.NoteDto;
import com.project.gcs18402.rentalz.dto.PropertiesDto;
import com.project.gcs18402.rentalz.entity.properties;
import com.project.gcs18402.rentalz.manager.PropertiesManager;
import com.project.gcs18402.rentalz.request.PropertiesRequest;
import com.project.gcs18402.rentalz.request.SearchRequest;
import com.project.gcs18402.rentalz.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    ResponseUtils responseUtils;

    @Autowired
    PropertiesManager propertiesManager;

    @GetMapping("/getall")
    public ResponseEntity<?> getall() {
        try {
            List<PropertiesDto> result = propertiesManager.getall();
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestBody PropertiesRequest req) {
        try {
            List<PropertiesDto> result = propertiesManager.getByUser(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PropertiesRequest req) {
        try {
            Boolean result = propertiesManager.createProps(req);
            if (result) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/advance-search")
    public ResponseEntity<?> advanceSearch(@RequestBody SearchRequest req) {
        try {
            List<PropertiesDto> result = propertiesManager.advanceSearch(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> createNote(@RequestBody PropertiesRequest req) {
        try {
            PropertiesDto result = propertiesManager.editProps(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createNote")
    public ResponseEntity<?> edit(@RequestBody PropertiesRequest req) {
        try {
            List<NoteDto> result = propertiesManager.addNote(req);
            if (result != null) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody PropertiesRequest req) {
        try {
            Boolean result = propertiesManager.deleteProps(req);
            if (result) {
                return responseUtils.getResponseEntity(result, 1, "success", HttpStatus.OK);
            }
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return responseUtils.getResponseEntity(null, -1, "fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
