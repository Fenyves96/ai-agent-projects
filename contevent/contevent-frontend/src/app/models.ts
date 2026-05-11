export type CreatorType = 'USER' | 'GROUP' | 'COMPANY';
export type EventStatus = 'OPEN' | 'CLOSED' | 'CANCELLED';
export type ApplicationStatus = 'PENDING' | 'ACCEPTED' | 'DENIED';

export interface Event {
  id: number;
  title: string;
  description: string;
  category: string;
  dateTime: string;
  location: string;
  maxParticipants: number;
  creatorId: number;
  creatorType: CreatorType;
  status: EventStatus;
}

export interface CreateEventRequest {
  title: string;
  description?: string;
  category?: string;
  dateTime: string;
  location?: string;
  maxParticipants: number;
  creatorId?: number;
  creatorType?: CreatorType;
}

export interface Application {
  id: number;
  eventId: number;
  applicantId: number;
  status: ApplicationStatus;
  message: string;
}

export interface CreateApplicationRequest {
  applicantId: number;
  message?: string;
}

export interface ReviewApplicationRequest {
  status: 'ACCEPTED' | 'DENIED';
}
