package com.project.gcs18402.rentalz.manager;

import com.project.gcs18402.rentalz.dto.CityDto;
import com.project.gcs18402.rentalz.dto.DistrictDto;
import com.project.gcs18402.rentalz.dto.WardDto;
import com.project.gcs18402.rentalz.entity.cityprovince;
import com.project.gcs18402.rentalz.entity.district;
import com.project.gcs18402.rentalz.entity.ward;
import com.project.gcs18402.rentalz.repository.CityRepository;
import com.project.gcs18402.rentalz.repository.DistrictRepository;
import com.project.gcs18402.rentalz.repository.WardRepository;
import com.project.gcs18402.rentalz.request.LocationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationManager {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    WardRepository wardRepository;

    public List<CityDto> getAllCity() {
        try {
            List<cityprovince> listQuery = cityRepository.getAllCity();
            List<CityDto> listRes = listQuery.stream().map(x -> modelMapper.map(x, CityDto.class)).collect(Collectors.toList());
            return listRes;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DistrictDto> getDistrictByCity(LocationRequest req) {
        try {
            List<district> listQuery = districtRepository.getDistrictByCity(req.getId());
            List<DistrictDto> listRes = listQuery.stream().map(x -> modelMapper.map(x, DistrictDto.class)).collect(Collectors.toList());
            return listRes;
        } catch (Exception e) {
            return null;
        }
    }

    public List<WardDto> getWardByDistrict(LocationRequest req) {
        try {
            List<ward> listQuery = wardRepository.getWardByDistrict(req.getId());
            List<WardDto> listRes = listQuery.stream().map(x -> modelMapper.map(x, WardDto.class)).collect(Collectors.toList());
            return listRes;
        } catch (Exception e) {
            return null;
        }
    }
}
