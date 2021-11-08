package com.project.gcs18402.rentalz.repository;

import com.project.gcs18402.rentalz.entity.note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<note, Long> {
    @Query("SELECT n FROM note n WHERE n.propertiesId = :id")
    List<note> getNoteByProps(@Param("id") Long id);
}
