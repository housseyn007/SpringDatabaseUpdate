package com.wildcodeschool.myProjectWithDB.controller;




import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;


@Controller
@ResponseBody
public class SchoolController {
   
    @GetMapping("/api/schools")
    
  public List<School> getSchool(@RequestParam (required = false ,defaultValue = "%")String country) {
    	return SchoolRepository.selectByCountry(country);    	  	
   }

    
    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(
    	    @RequestParam String name,
    	    @RequestParam int capacity,
    	    @RequestParam String country
    		
    ) {
    	int idGeneratedByInsertion = SchoolRepository.insert(
    		    name,
    		    capacity,
    		    country
    		        		);
    		return SchoolRepository.selectById(
    		    idGeneratedByInsertion
    		);
    	
    }
    @PutMapping("/api/schools/{id}")
    public School update(
      
        @PathVariable int id,

        @RequestParam(required = false) String name,

        @RequestParam(required = false) Integer capacity,

        @RequestParam(required = false) String country

        
    ) {

        School school = SchoolRepository.selectById(id);
        SchoolRepository.update(
            id,
            name != null ? name : school.getName(),
            capacity != null ? capacity : school.getCapacity(),
            country != null ? country : school.getCountry()
            
        );
    
    return SchoolRepository.selectById(id);
    }
    
    
}


