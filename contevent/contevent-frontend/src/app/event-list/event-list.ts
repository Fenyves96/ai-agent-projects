import { Component, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ConteventService } from '../contevent.service';
import { Event } from '../models';

@Component({
  selector: 'app-event-list',
  imports: [DatePipe, RouterLink],
  templateUrl: './event-list.html',
  styleUrl: './event-list.css',
})
export class EventList {
  private readonly service = inject(ConteventService);
  readonly events = signal<Event[]>([]);
  readonly loading = signal(true);

  constructor() {
    this.service.listEvents().subscribe({
      next: (list) => this.events.set(list),
      complete: () => this.loading.set(false),
    });
  }
}
