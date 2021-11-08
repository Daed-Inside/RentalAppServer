package com.project.gcs18402.rentalz.manager;

import com.project.gcs18402.rentalz.dto.NoteDto;
import com.project.gcs18402.rentalz.dto.PropertiesDto;
import com.project.gcs18402.rentalz.entity.note;
import com.project.gcs18402.rentalz.entity.properties;
import com.project.gcs18402.rentalz.repository.NoteRepository;
import com.project.gcs18402.rentalz.repository.PropertiesRepository;
import com.project.gcs18402.rentalz.request.PropertiesRequest;
import com.project.gcs18402.rentalz.request.SearchRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.NotLinkException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertiesManager {
    @Autowired
    PropertiesRepository propertiesRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<PropertiesDto> getall() {
        try {
            List<properties> queryData = propertiesRepository.findAll();
            return queryData.stream().map(x -> modelMapper.map(x, PropertiesDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<PropertiesDto> getByUser(PropertiesRequest req) {
        try {
            List<properties> queryData = propertiesRepository.getByUser(req.getUserId());
            return queryData.stream().map(x -> modelMapper.map(x, PropertiesDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean createProps(PropertiesRequest req) {
        try {
            properties newProps = new properties();
            newProps.setName(req.getName());
            newProps.setAddress(req.getAddress());
            newProps.setCity(req.getCity());
            newProps.setDistrict(req.getDistrict());
            newProps.setWard(req.getWard());
            newProps.setType(req.getType());
            newProps.setFurniture(req.getFurniture());
            newProps.setBedroom(Integer.valueOf(req.getBedroom()));
            newProps.setPrice(Double.valueOf(req.getPrice()));
            newProps.setReporter(req.getReporter());
            properties propsReturn = propertiesRepository.save(newProps);
            note newNotes = new note();
            newNotes.setContent(req.getNote());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(new Date());
            newNotes.setDate(date);
            newNotes.setPropertiesId(propsReturn.getId());
            noteRepository.save(newNotes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<PropertiesDto> advanceSearch(SearchRequest req) {
        try {
            List<properties> listQuery =  propertiesRepository.searchProps(req.getName() != null ? req.getName() : "",
                    req.getCity() != null ? req.getCity() : "",
                    req.getDistrict() != null ? req.getDistrict() : "",
                    req.getWard() != null ? req.getWard() : "",
                    req.getReporter() != null ? req.getReporter() : "",
                    req.getType() != null ? req.getType() : "",
                    req.getBedroom() != null ? !req.getBedroom().equals("0") ? Integer.valueOf(req.getBedroom()) : null : null,
                    req.getFurniture() != null ? req.getFurniture() : "",
                    req.getFromPrice() != null ? Double.valueOf(req.getFromPrice()) : null,
                    req.getToPrice() != null ? Double.valueOf(req.getToPrice()) : null,
                    req.getEmail() != null ? req.getEmail() : "");
            return listQuery.stream().map(x -> modelMapper.map(x, PropertiesDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<NoteDto> addNote(PropertiesRequest req) {
        try {
            note newNote = new note();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(new Date());
            newNote.setContent(req.getNote());
            newNote.setDate(date);
            newNote.setPropertiesId(req.getId());
            noteRepository.save(newNote);
            List<note> listQuery = noteRepository.getNoteByProps(req.getId());
            List<NoteDto> listRes = listQuery.stream().map(x -> modelMapper.map(x, NoteDto.class)).collect(Collectors.toList());
            return listRes;
        } catch (Exception e) {
            return null;
        }
    }

    public PropertiesDto editProps(PropertiesRequest req) {
        try {
            properties editedProp = propertiesRepository.findById(req.getId()).get();
            editedProp.setName(req.getName() != null ? req.getName() : editedProp.getName());
            editedProp.setAddress(req.getAddress() != null ? req.getAddress() : editedProp.getAddress());
            editedProp.setCity(req.getCity() != null ? req.getCity() : editedProp.getCity());
            editedProp.setDistrict(req.getDistrict() != null ? req.getDistrict() : editedProp.getDistrict());
            editedProp.setWard(req.getWard() != null ? req.getWard() : editedProp.getWard());
            editedProp.setType(req.getType() != null ? req.getType() : editedProp.getType());
            editedProp.setFurniture(req.getFurniture() != null ? req.getFurniture() : editedProp.getFurniture());
            editedProp.setBedroom(req.getBedroom() != null ? Integer.valueOf(req.getBedroom()) : editedProp.getBedroom());
            editedProp.setPrice(req.getPrice() != null ? Double.valueOf(req.getPrice()) : editedProp.getPrice());
            editedProp.setReporter(req.getReporter() != null ? req.getReporter() : editedProp.getReporter());
            properties returnal = propertiesRepository.save(editedProp);
            PropertiesDto res = modelMapper.map(returnal, PropertiesDto.class);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean deleteProps(PropertiesRequest req) {
        try {
            propertiesRepository.deleteById(req.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
