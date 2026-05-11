import { Routes } from '@angular/router';
import { EventList } from './event-list/event-list';
import { EventCreate } from './event-create/event-create';
import { EventDetail } from './event-detail/event-detail';

export const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'events', component: EventList },
  { path: 'events/new', component: EventCreate },
  { path: 'events/:eventId', component: EventDetail },
];
