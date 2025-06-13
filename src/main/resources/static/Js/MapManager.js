
const MALMO_BOUNDS = [
    [55.42,  12.85],
    [55.72,  13.28]
];

export class MapManager {
    constructor(containerId, center = [55.605,13.0038], zoom = 13) {
        this.map = L.map(containerId, {
            center,
            zoom,
            maxBounds: MALMO_BOUNDS,
            maxBoundsViscosity: 1,
            minZoom: 11,
            maxZoom: 15,
            scrollWheelZoom: true
        });
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(this.map);
    }

    getMap() {
        return this.map;
    }
}
