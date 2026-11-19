package com.devconnect.DevConnect.service;

import com.devconnect.DevConnect.model.Developer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    // In-memory storage shared with the service layer
    private final Map<Long, Developer> developerStore = new ConcurrentHashMap<>();

    /*
     * Retrieves all the developer profiles
     */
    @Override
    public List<Developer> getAllDevelopers() {
        return new ArrayList<>(developerStore.values());
    }

    /*
     * Retrieves a developer profile by ID
     */
    @Override
    public Developer getDeveloperById(Long id) {
        return developerStore.get(id);
    }

    /*
     * Search developers by skill (Case-Sensitive)
     */
    @Override
    public List<Developer> getDeveloperBySkill(String skill) {
        return developerStore.values().stream()
                .filter(developer -> developer.getSkills() != null
                        && developer.getSkills().contains(skill))
                .collect(Collectors.toList());
    }
}
