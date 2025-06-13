export class EventService {
    constructor(apiUrl = '/api/events') {
        this.apiUrl = apiUrl;
    }
    async loadEvents() {
        const res = await fetch(this.apiUrl);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
    }
}
