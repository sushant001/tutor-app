/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.StudentRepo;
import com.hansa.app.data.Student;
import com.hansa.app.data.User;
import com.hansa.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant Kumar
 */

@RestController
@RequestMapping("/student")
public class StudentResource {
    
    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(method = {RequestMethod.GET})
    public Iterable<Student> getTutors() {
        return studentRepo.findAll();
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(method = {RequestMethod.POST})
    public User save(@RequestBody Student student) {
        Student std= studentRepo.save(student);
        User user = new User();
        user.setRefId(std.getId());
        user.setType("STUDENT");
        user.setUserId(std.getMobile());
        userRepo.save(user);
        user.setDetail(std);
        return user;
    }
}
