package dh.meli.tutorials.service;

import dh.meli.tutorials.exception.GetException;
import dh.meli.tutorials.exception.SaveException;
import dh.meli.tutorials.exception.TutorialAlreadyExistsException;
import dh.meli.tutorials.exception.TutorialNotFoundException;
import dh.meli.tutorials.model.Tutorial;
import dh.meli.tutorials.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TutorialService<T> {

    @Autowired
    public TutorialRepository tutorialRepository;

    public Tutorial createTutorial(Tutorial tutorial) {
        Iterable<Tutorial> tutorialList = tutorialRepository.findAll();

        for (Tutorial existentTutorial : tutorialList) {
            if (existentTutorial.getCharCode().equals(tutorial.getCharCode())) {
                throw new TutorialAlreadyExistsException("Tutorial charCode attribute already exists");
            }
        }

        try {
            return tutorialRepository.save(tutorial);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }

    public List<Tutorial> getTutorial() {
        try {
            List<Tutorial> tutorialList = new ArrayList<Tutorial>();

            tutorialRepository.findAll().forEach(tutorialList::add);

            return tutorialList;
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }
    }

    public Tutorial getTutorialById(long id) {
        Optional<Tutorial> tutorial;

        try {
            tutorial = tutorialRepository.findById(id);
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }

        if (tutorial.isEmpty()) {
            throw new TutorialNotFoundException(String.format("Could not find tutorial for id %d", id));
        }

        return tutorial.get();
    }

    public List<Tutorial> getPublishedTutorial() {
        try {
            return tutorialRepository.getPublishedTutorials();
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }
    }

    public List<Tutorial> getTutorialByTitle(String title) {
        try {
            return tutorialRepository.getTutorialsByTitle(title);
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }
    }

    public Tutorial updateTutorial(long id, Map<String, T> data) {
        List<Tutorial> tutorialList = new ArrayList<Tutorial>();
        Optional<Tutorial> tutorial;

        try {
            tutorialRepository.findAll().forEach(tutorialList::add);

            tutorial = tutorialRepository.findById(id);
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }

        if (tutorial.isEmpty()) {
            throw new TutorialNotFoundException(String.format("Could not find tutorial for id %d", id));
        }

        data.forEach((attr, value) -> {
            switch (attr) {
                case "charCode": tutorial.get().setCharCode((String) value); break;
                case "title": tutorial.get().setTitle((String) value); break;
                case "description": tutorial.get().setDescription((String) value); break;
                case "published": tutorial.get().setPublished((boolean) value); break;
            }
        });

        for (Tutorial existentTutorial : tutorialList) {
            boolean hasDifferentId = existentTutorial.getId() != tutorial.get().getId();
            boolean hasSameCharCode = existentTutorial.getCharCode().equals(tutorial.get().getCharCode());

            if (hasDifferentId && hasSameCharCode) {
                throw new TutorialAlreadyExistsException(
                        String.format("Tutorial charCode %s attribute already exists",
                                tutorial.get().getCharCode())
                );
            }
        }

        try {
            return tutorialRepository.save(tutorial.get());
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }

    public void deleteTutorial() {
        tutorialRepository.deleteAll();
    }

    public void deleteTutorialById(long id) {
        Tutorial tutorial = this.getTutorialById(id);

        tutorialRepository.delete(tutorial);
    }
}
