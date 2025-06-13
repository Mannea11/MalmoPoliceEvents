export class StatsController {
    constructor() {
        this.charts = {};
    }

    _getTopCrimes(events) {
        const counts = {};
        events.forEach((e) => {
            const type = e.title.split(',')[1]?.trim() || 'Okänt';
            counts[type] = (counts[type] || 0) + 1;
        });
        return Object.entries(counts).sort((a, b) => b[1] - a[1]);
    }

    _getTopAreas(events) {
        const counts = {};
        events.forEach((e) => {
            if (e.area === 'Malmö') return;
            const area = e.area || 'Okänt';
            counts[area] = (counts[area] || 0) + 1;
        });
        return Object.entries(counts).sort((a, b) => b[1] - a[1]);
    }

    _drawPieChart(canvasId, labels, data) {
        const ctx = document.getElementById(canvasId).getContext('2d');
        if (this.charts[canvasId]) {
            this.charts[canvasId].destroy();
        }
        this.charts[canvasId] = new Chart(ctx, {
            type: 'pie',
            data: {
                labels,
                datasets: [
                    {
                        data,
                        backgroundColor: [
                            '#4a90e2',
                            '#50e3c2',
                            '#7b4397',
                            '#d64550',
                            '#f39c12'
                        ],
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: false,
                maintainAspectRatio: false,
                plugins: {
                    title: {
                        display: true,
                        text:
                            canvasId === 'crimeChart'
                                ? 'Topp 5 brottstyper'
                                : 'Topp 5 områden'
                    },
                    legend: {
                        position: 'bottom',
                        labels: { boxWidth: 12, padding: 16 }
                    },
                    tooltip: {
                        callbacks: {
                            label: (ctx) => {
                                const val = ctx.parsed;
                                const total = ctx.dataset.data.reduce((sum, v) => sum + v, 0);
                                const pct = ((val / total) * 100).toFixed(1);
                                return `${ctx.label}: ${val} (${pct}%)`;
                            }
                        }
                    }
                }
            }
        });
    }

    render(filtered, map) {
        const topCrimes = this._getTopCrimes(filtered).slice(0, 5);
        const topAreas = this._getTopAreas(filtered).slice(0, 5);

        this._drawPieChart(
            'crimeChart',
            topCrimes.map(([t]) => t),
            topCrimes.map(([, c]) => c)
        );
        this._drawPieChart(
            'areaChart',
            topAreas.map(([a]) => a),
            topAreas.map(([, c]) => c)
        );
        map.invalidateSize();
    }
}
