package com.project.gcs18402.rentalz.repository;

import com.project.gcs18402.rentalz.entity.ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<ward, String> {
    @Query("SELECT w FROM ward w WHERE w.districtId = :districtId")
    List<ward> getWardByDistrict(@Param("districtId") String districtId);
}
