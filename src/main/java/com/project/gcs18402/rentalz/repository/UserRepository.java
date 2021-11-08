package com.project.gcs18402.rentalz.repository;

import com.project.gcs18402.rentalz.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<user, Long> {
    @Query("SELECT u FROM user u WHERE u.email = :email")
    user getUserByEmail(@Param("email") String email);
}
