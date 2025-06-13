export class MarkerRenderer {
    constructor(definedCluster, undefinedCluster) {
        this.definedCluster = definedCluster;
        this.undefinedCluster = undefinedCluster;
    }

    render(filtered) {
        this.definedCluster.clearLayers();
        this.undefinedCluster.clearLayers();

        filtered.forEach((e) => {
            const { latitude: lat, longitude: lng, area } = e;
            if (typeof lat !== 'number' || typeof lng !== 'number') return;

            const popup = `
        <strong>${e.title}</strong><br>
        <em>${new Date(e.pubDate).toLocaleString('sv-SE')}</em><br>
        <p>${e.description}</p>
        <small>Område: ${e.area}</small><br>
        <a href="${e.link}" target="_blank">Läs mer</a>
      `;
            const marker = L.marker([lat, lng]).bindPopup(popup);

            if (area === 'Malmö') {
                this.undefinedCluster.addLayer(marker);
            } else {
                this.definedCluster.addLayer(marker);
            }
        });
    }
}
