export class EventListController {
    constructor(listContainerId, map) {
        this.listEl = document.getElementById(listContainerId);
        this.map    = map;
    }

    populate(allEvents) {
        const sorted = [...allEvents].sort((a, b) => {
            return new Date(b.pubDate) - new Date(a.pubDate);
        });

        const latestTen = sorted.slice(0, 10);

        this.listEl.innerHTML = '';

        if (latestTen.length === 0) {
            const li = document.createElement('li');
            li.textContent = 'Inga händelser att visa.';
            this.listEl.appendChild(li);
            return;
        }

        latestTen.forEach((e) => {
            const li = document.createElement('li');
            const dateStr = new Date(e.pubDate).toLocaleDateString('sv-SE');
            const type = e.title.split(',')[1]?.trim() || 'Okänt';
            li.textContent = `${dateStr} – ${type} (${e.area || 'Okänt'})`;

            li.addEventListener('click', () => {
                if (
                    typeof e.latitude === 'number' &&
                    typeof e.longitude === 'number'
                ) {
                    this.map.setView([e.latitude, e.longitude], 13, { animate: true });
                }
            });

            this.listEl.appendChild(li);
        });
    }
}
