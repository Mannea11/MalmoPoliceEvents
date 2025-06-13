
package Crime.Service;

import Crime.Model.Event;
import Crime.Repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncService {
    private final RssService    rssService;
    private final GeocodingService geoService;
    private final EventRepository repo;

    public SyncService(RssService rssService,
                       GeocodingService geoService,
                       EventRepository repo) {
        this.rssService    = rssService;
        this.geoService    = geoService;
        this.repo          = repo;
    }
    @Scheduled(cron = "0 */10 * * * *")
    public void fetchAndSave() throws Exception {
        List<Crime.Model.Event> events = rssService.fetchMalmöEvents();
        for (Crime.Model.Event e : events) {
            if (!repo.existsByTitleAndPubDate(e.getTitle(), e.getPubDate())) {
                Event event = new Event();
                event.setTitle(e.getTitle());
                event.setLink(e.getLink());
                event.setDescription(e.getDescription());
                event.setPubDate(e.getPubDate());
                event.setArea(e.getArea());

                geoService.geocode(e.getArea()).ifPresent(coords -> {
                    event.setLatitude(coords[0]);
                    event.setLongitude(coords[1]);
                });

                repo.save(event);
                Thread.sleep(1000);
            }
        }
    }
}
