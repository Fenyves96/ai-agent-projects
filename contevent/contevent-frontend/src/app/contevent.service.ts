import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event, CreateEventRequest, Application, CreateApplicationRequest, ReviewApplicationRequest } from './models';

@Injectable({ providedIn: 'root' })
export class ConteventService {
  private readonly http = inject(HttpClient);
  private readonly api = 'http://localhost:8080';

  listEvents(category?: string): Observable<Event[]> {
    const params = category ? `?category=${encodeURIComponent(category)}` : '';
    return this.http.get<Event[]>(`${this.api}/events${params}`);
  }

  getEvent(eventId: number): Observable<Event> {
    return this.http.get<Event>(`${this.api}/events/${eventId}`);
  }

  createEvent(req: CreateEventRequest): Observable<Event> {
    return this.http.post<Event>(`${this.api}/events`, req);
  }

  getApplications(eventId: number): Observable<Application[]> {
    return this.http.get<Application[]>(`${this.api}/events/${eventId}/applications`);
  }

  applyToEvent(eventId: number, req: CreateApplicationRequest): Observable<Application> {
    return this.http.post<Application>(`${this.api}/events/${eventId}/applications`, req);
  }

  reviewApplication(eventId: number, applicationId: number, req: ReviewApplicationRequest): Observable<Application> {
    return this.http.put<Application>(`${this.api}/events/${eventId}/applications/${applicationId}`, req);
  }
}
