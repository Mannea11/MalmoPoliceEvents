export class ToggleController {
    constructor(toggleBtnId, map, undefinedCluster) {
        this.show = false;
        this.map = map;
        this.cluster = undefinedCluster;
        this.button = document.getElementById(toggleBtnId);

        this.button.addEventListener('click', () => {
            this.show = !this.show;
            if (this.show) {
                this.map.addLayer(this.cluster);
                this.button.textContent = 'Dölj odefinierade områden';
            } else {
                this.map.removeLayer(this.cluster);
                this.button.textContent = 'Visa odefinierade områden';
            }
        });
    }
}
