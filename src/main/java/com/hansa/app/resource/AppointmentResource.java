/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.StudentRepo;
import com.hansa.app.repo.AppointmentRepo;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.data.Appointment;
import com.hansa.app.service.EmailService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant Kumar
 */

@CrossOrigin(origins = "*")
@RestController
public class AppointmentResource {
    
    @Autowired
    private AppointmentRepo appointmentRepo;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired
    private StudentRepo  studentRepo;
    
    
    @RequestMapping(path = "/appointment/student/{id}", method = {RequestMethod.GET})
    public Iterable<Appointment> appointForStudent(@PathVariable("id") Long id) {
        return appointmentRepo.getByStudent(id);
    }
    
    
    @RequestMapping(path = "/appointment/tutor/{id}", method = {RequestMethod.GET})
    public Iterable<Appointment> appointForTutor(@PathVariable("id") Long id) {
        return appointmentRepo.getByTutor(id);
    }
    
    
    @RequestMapping(path = "/appointment/{id}", method = {RequestMethod.PUT})
    public Appointment update(@PathVariable("id") Long id, @RequestParam("status") String status) {
        
        Appointment app = appointmentRepo.get(id);
        app.setStatus(status);
        app.setDate(new Date());
        
        emailService.sendEmail("Your appointment is updated to Status "+status, "Updateed Appointment", app.getTutor().getEmail());
        return appointmentRepo.save(app);
    }
    
    
    
    @RequestMapping(path = "/appointment", method = {RequestMethod.POST})
    public Appointment save(@RequestBody Appointment appointment) {
        appointment.setStatus("CREATED");
        appointment.setDate(new Date());
        emailService.sendEmail("You have got a new appointment, Student "+appointment.getStudent().getEmail(), "New Request", appointment.getTutor().getEmail());
        return appointmentRepo.save(appointment);
    }
    
    
}
