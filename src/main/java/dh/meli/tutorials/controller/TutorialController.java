package dh.meli.tutorials.controller;

import dh.meli.tutorials.dto.TutorialDto;
import dh.meli.tutorials.model.Tutorial;
import dh.meli.tutorials.service.TutorialService;
import dh.meli.tutorials.util.TutorialIntoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TutorialController<T> {

    @Autowired
    public TutorialService tutorialService;

    @Autowired
    public TutorialIntoDto tutorialIntoDto;

    @PostMapping("/tutorial")
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial savedTutorial = tutorialService.createTutorial(tutorial);

        return new ResponseEntity<TutorialDto>(new TutorialDto(savedTutorial), HttpStatus.OK);
    }

    @GetMapping("/tutorial")
    public ResponseEntity<List<TutorialDto>> getTutorial() {
        List<Tutorial> tutorialList = tutorialService.getTutorial();

        List<TutorialDto> tutorialDtoList = tutorialIntoDto.convert(tutorialList);

        return new ResponseEntity<List<TutorialDto>>(tutorialDtoList, HttpStatus.OK);
    }

    @GetMapping("/tutorial/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable long id) {
        Tutorial tutorial = tutorialService.getTutorialById(id);

        return new ResponseEntity<TutorialDto>(new TutorialDto(tutorial), HttpStatus.OK);
    }

    @GetMapping("/tutorial/published")
    public ResponseEntity<List<TutorialDto>> getPublishedTutorial() {
        List<Tutorial> tutorialList = tutorialService.getPublishedTutorial();

        List<TutorialDto> tutorialDtoList = tutorialIntoDto.convert(tutorialList);

        return new ResponseEntity<List<TutorialDto>>(tutorialDtoList, HttpStatus.OK);
    }

    @GetMapping("/tutorial/title/{title}")
    public ResponseEntity<List<TutorialDto>> getTutorialByTitle(@PathVariable String title) {
        List<Tutorial> tutorialList = tutorialService.getTutorialByTitle(title);

        List<TutorialDto> tutorialDtoList = tutorialIntoDto.convert(tutorialList);

        return new ResponseEntity<List<TutorialDto>>(tutorialDtoList, HttpStatus.OK);
    }

    @PatchMapping("/tutorial/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(@PathVariable long id, @RequestBody Map<String, T> data) {
        Tutorial tutorial = tutorialService.updateTutorial(id, data);

        return new ResponseEntity<TutorialDto>(new TutorialDto(tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/tutorial")
    public ResponseEntity<Void> deleteTutorial() {
        tutorialService.deleteTutorial();

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/tutorial/{id}")
    public ResponseEntity<Void> deleteTutorialById(@PathVariable long id) {
        tutorialService.deleteTutorialById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
