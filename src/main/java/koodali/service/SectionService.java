package koodali.service;

import koodali.model.AttendanceEntity;
import koodali.model.Section;
import koodali.model.Student;
import koodali.model.dto.AttendanceDTO;
import koodali.model.dto.SectionDTO;
import koodali.repository.AttendanceRepository;
import koodali.repository.SectionRepository;
import koodali.service.exceptions.DuplicateSectionException;
import koodali.service.exceptions.InvalidSectionException;
import koodali.service.exceptions.SectionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    private final AttendanceRepository attendanceRepository;





    public SectionService(SectionRepository sectionRepository, AttendanceRepository attendanceRepository) {

        this.sectionRepository = sectionRepository;
        this.attendanceRepository = attendanceRepository;
    }

    /**
     * Get mapping
     * gets all sections
     */
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public List<SectionDTO> getAllSectionsDTO() {
        return sectionRepository.findAll().stream().map(this::SectionToDTO).toList();
    }

    /**
     * gets section by its id and throws a SectionNotFoundException if it doesn't work
     *
     * @param className class ID
     * @return Section
     * @throws SectionNotFoundException "didn't find section"
     */
    public Section getSectionByName(String className) {
        return Optional.of(sectionRepository.getReferenceById(className)).orElseThrow(SectionNotFoundException::new);
    }

    public SectionDTO getSectionDTOByName(String className) {

        return Optional.of(SectionToDTO(sectionRepository.getReferenceById(className))).orElseThrow(SectionNotFoundException::new);
    }

    public Section getSectionByNumericID(int classId) {
        return sectionRepository.findAll().stream().filter(s -> s.getId() == classId).findFirst().orElseThrow(SectionNotFoundException::new);
    }

    public void updateSection(Section section) {
        sectionRepository.save(section);
    }

    public SectionDTO updateSection(SectionDTO sectionDTO, int id) {
        Section s = getSectionByNumericID(id);
        s.setLinkOrAddress(sectionDTO.linkOrAddress());
        s.setName(sectionDTO.name());
       return SectionToDTO(sectionRepository.save(s));

    }

    private boolean validateName(String sectionName) {
        return sectionName.matches("[A-Z][A-Z]_[BIA][END]T*([GV]|[_0-9])*");
    }

    private boolean checkForDuplicate(String sectionName){
        return  sectionRepository.findAll().stream().noneMatch(s -> s.getName().equals(sectionName));
    }

    public String returnSectionNameIfValid(String sectionName){
        if (validateName(sectionName)) {
            return  sectionName;

        } else {
            throw new InvalidSectionException();
        }
    }


    public SectionDTO createSection(SectionDTO sectionDTO) {

        if (validateName(sectionDTO.name())) {
            if(checkForDuplicate(sectionDTO.name())){
                Section s = sectionRepository.save(DTOToSection(sectionDTO));
                return SectionToDTO(s);
            }else{
                throw new DuplicateSectionException();
            }

        } else {
            throw new InvalidSectionException();
        }

    }

    private SectionDTO SectionToDTO(Section section) {
        if (section == null) {
            throw new SectionNotFoundException();
        }

        return new SectionDTO(section.getId()
                , section.getName()
                , section.getLinkOrAddress());
    }

    private Section DTOToSection(SectionDTO dto) {
        return new Section(dto.name(), dto.linkOrAddress());
    }

    public SectionDTO deleteSectionbyName(String sectionName) {
        if (sectionRepository.existsById(sectionName)) {
            Section s = sectionRepository.getReferenceById(sectionName);
            sectionRepository.delete(s);
            return SectionToDTO(s);

        } else {
            throw new SectionNotFoundException();
        }

    }



    //addStudents
    //removeStudents -> warn when students.amount <=1 that it is better to delete the section

}



