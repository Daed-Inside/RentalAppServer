package com.project.gcs18402.rentalz.manager;

import com.project.gcs18402.rentalz.dto.UserDto;
import com.project.gcs18402.rentalz.entity.user;
import com.project.gcs18402.rentalz.repository.UserRepository;
import com.project.gcs18402.rentalz.request.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthManager {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    public Map<String, Object> Login(LoginRequest req) {
        try {
            user loginUser = userRepository.getUserByEmail(req.getEmail());
            Map<String, Object> mapReturn = new HashMap<>();
            if (loginUser == null) {
                mapReturn.put("message", "exist");
                return mapReturn;
            } else if (!loginUser.getPassword().equals(req.getPassword())) {
                mapReturn.put("message", "password");
                return mapReturn;
            }
            mapReturn.put("message", "success");
            mapReturn.put("data", modelMapper.map(loginUser, UserDto.class));
            return mapReturn;
        } catch (Exception e) {
            Map<String, Object> mapReturn = new HashMap<>();
            mapReturn.put("message", "fail");
            return mapReturn;
        }
    }

    public String Signup(LoginRequest req) {
        try {
            user newUser = new user();
            newUser.setEmail(req.getEmail());
            newUser.setPassword(req.getPassword());
            newUser.setAddress(req.getAddress());
            newUser.setPhone(req.getPhone());
            userRepository.save(newUser);
            return "success";
        } catch (Exception e) {
            return "email exist";
        }
    }
}
