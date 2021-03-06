/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.ClassGroup;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sushant
 */

public interface ClassGroupRepo extends CrudRepository<ClassGroup, Long> {
    
    @Query("select cg from ClassGroup cg")
    public List<ClassGroup> all();
}
