package com.devconnect.DevConnect.controller;

import com.devconnect.DevConnect.model.Developer;
import com.devconnect.DevConnect.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    // In Memory Storage
    // ConcurrentHashMap - Thread safe hashmap - safe for concurrent requests.
    private final Map<Long, Developer> developerStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);


    @GetMapping
    public List<Developer> getAllDevelopers() {

        return ResponseEntity.ok(developerService.getAllDevelopers()).getBody();
    }

    // Path Variable
    @GetMapping("/{id}")
    public Developer getDeveloperByID(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloperById(id)).getBody();
    }

    @GetMapping("/search")
    public List<Developer> getDevelopersBySkill(
            @RequestParam String skill,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int cpunt
    ) {

        return ResponseEntity.ok().body(developerService.getDeveloperBySkill(skill)).getBody();
    }

    @PostMapping

    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {

        return ResponseEntity.status(HttpStatus.CREATED).body(developerService.createDeveloper(developer));


        // for(Developer existingDeveloper : developerStore.values()) {
        //     if(existingDeveloper.getPhoneNumber().equals(developer.getPhoneNumber()) ||
        //     existingDeveloper.getEmail().equals(developer.getEmail())) {
        //         return ResponseEntity.status(HttpStatus.CONFLICT).body(developer);
        //     }
        // }


        // developer.setId(idCounter.getAndIncrement());

        // if(developer.getId() > 3) {
        //     developer.addSkill("React");
        //     developer.addSkill("JS");
        //     developer.addSkill("Numpy");
        // }
        // developer.addSkill("Python");
        // developer.addSkill("C++");
        // developer.addSkill("TensorFlow");

        // developerStore.put(developer.getId(), developer);

        // return ResponseEntity.status(HttpStatus.CREATED).body(developer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> replaceDeveloper(@PathVariable Long id, @RequestBody Developer developer) {
        // if(!developerStore.containsKey(id)) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Developer());
        // }
        // developerStore.remove(id);
        // developerStore.put(id, developer);

        // return ResponseEntity.ok(developer);

        return ResponseEntity.ok(developerService.replaceDeveloper(id, developer));
    }

}
