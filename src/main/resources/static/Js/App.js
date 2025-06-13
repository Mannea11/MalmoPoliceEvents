import { MapManager }   from './MapManager.js';
import { EventService } from './EventService.js';
import { FilterController } from './FilterController.js';
import { MarkerRenderer } from './MarkerRenderer.js';
import { StatsController } from './StatsController.js';
import { ToggleController } from './ToggleController.js';
import { EventListController } from './EventListController.js';

class App {
    constructor() {
        this.mapManager       = new MapManager('map', [55.605, 13.0038], 11);
        this.map              = this.mapManager.getMap();
        this.definedCluster   = L.markerClusterGroup();
        this.undefinedCluster = L.markerClusterGroup();
        this.map.addLayer(this.definedCluster);
        this.crimeFilter = document.getElementById('crimeFilter');
        this.dateSlider  = document.getElementById('dateSlider');
        this.dateLabel   = document.getElementById('dateLabel');
        this.totalCount  = document.getElementById('totalCount');

        this.toggleController = new ToggleController(
            'toggleUndefined',
            this.map,
            this.undefinedCluster
        );
        this.eventListController = new EventListController('eventList', this.map);
        this.eventService = new EventService();
        this.allEvents    = [];
        this.filtered     = [];

        this.filterController = null;
        this.markerRenderer   = null;
        this.statsController  = null;
    }

    async run() {
        this.allEvents = await this.eventService.loadEvents();
        this.eventListController.populate(this.allEvents);
        this.filterController = new FilterController(
            this.crimeFilter,
            this.dateSlider,
            this.dateLabel
        );
        this.filterController.initFilters(this.allEvents);
        this.markerRenderer = new MarkerRenderer(
            this.definedCluster,
            this.undefinedCluster
        );
        this.statsController = new StatsController();
        const render = () => {
            this.filtered = this.filterController.getFiltered(this.allEvents);
            this.markerRenderer.render(this.filtered);
            this.statsController.render(this.filtered, this.map);
            this.totalCount.textContent = this.filtered.length;
        };
        this.filterController.attachListeners(render);
        render();
    }
}

window.addEventListener('DOMContentLoaded', () => {
    new App()
        .run()
        .catch((err) => {
            console.error(err);
            alert('Kunde inte ladda händelser.');
        });
});
