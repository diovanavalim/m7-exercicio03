package dh.meli.tutorials.util;

import dh.meli.tutorials.dto.TutorialDto;
import dh.meli.tutorials.model.Tutorial;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TutorialIntoDto {

    public List<TutorialDto> convert(List<Tutorial> tutorialList) {
        List<TutorialDto> tutorialDtoList = new ArrayList<TutorialDto>();

        for (Tutorial tutorial : tutorialList) {
            tutorialDtoList.add(new TutorialDto(tutorial));
        }

        return tutorialDtoList;
    }
}
