/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */
public interface JobRepo extends JpaRepository<Job, Long> {
    
    @Query("select j from Job j where id =:id")
    Job get(@Param("id") Long id); 
    
    @Query("select j from Job j where j.student.id =:studentId")
    Page<Job> get(@Param("studentId") Long id, Pageable page); 
    
    
}
