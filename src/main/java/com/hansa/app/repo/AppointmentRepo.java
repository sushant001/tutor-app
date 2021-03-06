/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface AppointmentRepo extends CrudRepository<Appointment, Long> {
    
    
    @Query("select a from Appointment a where a.student.id =:studentId ")
    List<Appointment> getByStudent(@Param("studentId") Long studentId) ;
    
    @Query("select a from Appointment a where a.tutor.id =:tutorId ")
    List<Appointment> getByTutor(@Param("tutorId") Long tutorId) ;
    
    @Query("select a from Appointment a where a.id =:id ")
    Appointment get(@Param("id") Long tutoridId) ;
    
}
