package Crime.Config;

import Crime.Model.Event;
import Crime.Repository.EventRepository;
import Crime.Service.RssService;
import Crime.Service.GeocodingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RssService rssService,
                           GeocodingService geoService,
                           EventRepository repo) {
        return args -> {
            rssService.fetchMalmöEvents().forEach(evt -> {
                if (repo.existsByLink(evt.getLink())) {
                    return;
                }

                geoService.geocode(evt.getArea()).ifPresent(coords -> {
                    evt.setLatitude(coords[0]);
                    evt.setLongitude(coords[1]);
                });

                Event e = new Event();
                e.setTitle(evt.getTitle());
                e.setLink(evt.getLink());
                e.setDescription(evt.getDescription());
                e.setPubDate(evt.getPubDate());
                e.setArea(evt.getArea());
                e.setLatitude(evt.getLatitude());
                e.setLongitude(evt.getLongitude());
                repo.save(e);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        };
    }
}
