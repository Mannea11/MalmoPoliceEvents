package Crime.Repository;

import Crime.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByTitleAndPubDate(String title, String pubDate);
    boolean existsByLink(String link);
    Optional<Event> findByLink(String link);
}
