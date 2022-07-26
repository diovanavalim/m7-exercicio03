package dh.meli.tutorials.repository;

import dh.meli.tutorials.model.Tutorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends CrudRepository<Tutorial, Long> {
    @Query(value = "SELECT * FROM tutorial WHERE published = true", nativeQuery = true)
    public List<Tutorial> getPublishedTutorials();

    @Query(value = "SELECT * FROM tutorial WHERE title LIKE ?1", nativeQuery = true)
    public List<Tutorial> getTutorialsByTitle(String title);
}
