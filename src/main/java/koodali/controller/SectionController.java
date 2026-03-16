package koodali.controller;

import koodali.model.dto.AttendanceDTO;
import koodali.model.dto.SectionDTO;
import koodali.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     *--------------------- GET Mappings------------------------------------
     *
     * */

    /**
     * gets all sections and returns them as a List
     */


    @GetMapping("/sections")
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAllSectionsDTO(), HttpStatus.OK);
    }

    /**
     * gets a specified Section. Can throw SectionNotFoundException
     */
    @GetMapping("/sections/{id}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable String id) {
        return new ResponseEntity<>(sectionService.getSectionDTOByName(id), HttpStatus.OK);
    }





    /**
     * -------------------- POST Mappings -------------------------------
     * PostMapping("/sections")
     * public ResponseEntity<Section> createSection(@RequestBody Section newSection) {
     * return new ResponseEntity<>(sectionService.createSection(newSection), HttpStatus.CREATED);
     * }
     */


    @PostMapping("/sections")
    public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO newSection) {
        return new ResponseEntity<>(sectionService.createSection(newSection), HttpStatus.CREATED);
    }

    /**
     * -------------------- PUT Mappings -------------------------------
     * PutMapping("/sections")
     * public ResponseEntity<Section> updateSection(@RequestBody Section updatedSection) {
     * return new ResponseEntity<>(sectionService.updateSection(updatedSection), HttpStatus.OK);
     * }
     */


    @PutMapping("/sections/{id}")
    public ResponseEntity<SectionDTO> updateSection(@PathVariable int id,@RequestBody SectionDTO updatedSection) {
        return new ResponseEntity<>(sectionService.updateSection(updatedSection,id), HttpStatus.OK);
    }

    /**
     * -------------------- DELETE Mappings -------------------------------
     * */

}
