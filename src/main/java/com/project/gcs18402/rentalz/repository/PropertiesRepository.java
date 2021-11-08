package com.project.gcs18402.rentalz.repository;

import com.project.gcs18402.rentalz.entity.properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<properties, Long> {
    @Query(value = "SELECT p.* FROM properties p\n" +
            "LEFT JOIN user u on u.id = p.user_id\n" +
            "WHERE p.name like CONCAT('%',:name,'%') and p.city like CONCAT('%',:city,'%')\n" +
            "and p.district like CONCAT('%',:district,'%') and p.ward like CONCAT('%',:ward,'%')\n" +
            "and p.reporter like CONCAT('%',:reporter,'%') and p.type like CONCAT('%',:type,'%')\n" +
            "and (CASE WHEN :bedroom is null THEN p.bedroom BETWEEN 1 AND 10000 ELSE p.bedroom = :bedroom END)\n" +
            "and p.furniture like CONCAT('%',:furniture,'%')\n" +
            "and (CASE WHEN :fromPrice is null AND :toPrice is null THEN\n" +
            "p.price > 0 ELSE p.price BETWEEN :fromPrice AND :toPrice END) and u.email like CONCAT('%',:email,'%')", nativeQuery = true)
    List<properties> searchProps(@Param("name") String name, @Param("city") String city,
                                 @Param("district") String district, @Param("ward") String ward,
                                 @Param("reporter") String reporter, @Param("type") String type,
                                 @Param("bedroom") Integer bedroom, @Param("furniture") String furniture,
                                 @Param("fromPrice") Double fromPrice, @Param("toPrice") Double toPrice, @Param("email") String email);

    @Query("SELECT p FROM properties p WHERE p.userId = :userId")
    List<properties> getByUser(@Param("userId") Long userId);
}
