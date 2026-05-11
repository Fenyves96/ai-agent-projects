import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ConteventService } from '../contevent.service';
import { CreateEventRequest } from '../models';

@Component({
  selector: 'app-event-create',
  imports: [FormsModule, RouterLink],
  templateUrl: './event-create.html',
  styleUrl: './event-create.css',
})
export class EventCreate {
  private readonly service = inject(ConteventService);
  private readonly router = inject(Router);

  readonly submitting = signal(false);
  readonly error = signal('');

  model: CreateEventRequest = {
    title: '',
    description: '',
    category: '',
    dateTime: '',
    location: '',
    maxParticipants: 10,
    creatorId: 1,
    creatorType: 'USER',
  };

  submit() {
    if (!this.model.title || !this.model.dateTime) return;
    this.submitting.set(true);
    this.error.set('');
    const payload = {
      ...this.model,
      dateTime: this.model.dateTime.includes('T') ? this.model.dateTime + ':00' : this.model.dateTime,
    };
    this.service.createEvent(payload).subscribe({
      next: (event) => this.router.navigate(['/events', event.id]),
      error: () => {
        this.error.set('Failed to create event.');
        this.submitting.set(false);
      },
    });
  }
}
