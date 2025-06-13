export class FilterController {
    constructor(crimeFilter, dateSlider, dateLabel) {
        this.crimeFilter = crimeFilter;
        this.dateSlider = dateSlider;
        this.dateLabel = dateLabel;
    }

    initFilters(allEvents) {
        const types = Array.from(
            new Set(
                allEvents.map((e) => e.title.split(',')[1]?.trim() || '')
            )
        ).sort((a, b) => a.localeCompare(b, 'sv'));

        types.forEach((t) => {
            if (!t) return;
            const opt = document.createElement('option');
            opt.value = t;
            opt.textContent = t;
            this.crimeFilter.append(opt);
        });

        const times = allEvents.map((e) => new Date(e.pubDate).getTime());
        const minT = Math.min(...times);
        const maxT = Math.max(...times);
        this.dateSlider.min = minT;
        this.dateSlider.max = maxT;
        this.dateSlider.value = minT;
        this._updateDateLabel(minT);
    }

    attachListeners(onFilterChanged) {
        this.crimeFilter.addEventListener('change', () => onFilterChanged());
        this.dateSlider.addEventListener('input', () => {
            this._updateDateLabel(this.dateSlider.value);
            onFilterChanged();
        });
    }

    _updateDateLabel(ts) {
        this.dateLabel.textContent = new Date(+ts).toLocaleDateString('sv-SE');
    }

    getFiltered(allEvents) {
        const selType = this.crimeFilter.value;
        const minDate = +this.dateSlider.value;

        return allEvents.filter((e) => {
            const type = e.title.split(',')[1]?.trim() || '';
            const date = new Date(e.pubDate).getTime();
            return (!selType || type === selType) && date >= minDate;
        });
    }
}
