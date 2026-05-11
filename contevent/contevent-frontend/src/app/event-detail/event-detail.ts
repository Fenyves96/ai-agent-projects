import { Component, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ConteventService } from '../contevent.service';
import { Event, Application } from '../models';

@Component({
  selector: 'app-event-detail',
  imports: [DatePipe, RouterLink],
  templateUrl: './event-detail.html',
  styleUrl: './event-detail.css',
})
export class EventDetail {
  private readonly route = inject(ActivatedRoute);
  private readonly service = inject(ConteventService);

  readonly event = signal<Event | undefined>(undefined);
  readonly applications = signal<Application[]>([]);
  readonly loading = signal(true);

  private eventId: number = 0;

  constructor() {
    this.eventId = Number(this.route.snapshot.paramMap.get('eventId'));
    this.service.getEvent(this.eventId).subscribe({
      next: (e) => {
        this.event.set(e);
        this.loading.set(false);
        this.loadApplications();
      },
      error: () => this.loading.set(false),
    });
  }

  private loadApplications() {
    this.service.getApplications(this.eventId).subscribe({
      next: (apps) => this.applications.set(apps),
    });
  }

  accept(appId: number) {
    this.service.reviewApplication(this.eventId, appId, { status: 'ACCEPTED' }).subscribe({
      next: () => this.loadApplications(),
    });
  }

  deny(appId: number) {
    this.service.reviewApplication(this.eventId, appId, { status: 'DENIED' }).subscribe({
      next: () => this.loadApplications(),
    });
  }

  apply() {
    const applicantId = prompt('Enter your user ID:');
    if (!applicantId) return;
    this.service.applyToEvent(this.eventId, { applicantId: Number(applicantId) }).subscribe({
      next: () => this.loadApplications(),
    });
  }
}
