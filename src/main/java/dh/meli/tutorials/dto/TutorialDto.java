package dh.meli.tutorials.dto;

import dh.meli.tutorials.model.Tutorial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TutorialDto {
    private long id;
    private String title;
    private String description;
    private boolean published;

    public TutorialDto(Tutorial tutorial) {
        this.id = tutorial.getId();
        this.title = tutorial.getTitle();
        this.description = tutorial.getDescription();
        this.published = tutorial.isPublished();
    }
}
