package com.project.gcs18402.rentalz.repository;

import com.project.gcs18402.rentalz.entity.district;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<district, String> {
    @Query("SELECT d FROM district d WHERE d.cityId = :cityId")
    List<district> getDistrictByCity(@Param("cityId") String cityId);
}
