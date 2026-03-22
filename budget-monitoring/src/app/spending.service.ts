import { Injectable, signal, computed } from '@angular/core';
import { Spending } from './spending.model';

@Injectable({
  providedIn: 'root',
})
export class SpendingService {
  private readonly spendings = signal<Spending[]>([
    { id: '1', title: 'Groceries', amount: 85.5, category: 'Food', date: '2026-03-20' },
    { id: '2', title: 'Gas', amount: 45.0, category: 'Transport', date: '2026-03-19' },
    { id: '3', title: 'Coffee', amount: 12.5, category: 'Food', date: '2026-03-18' },
  ]);

  readonly allSpendings = this.spendings.asReadonly();

  readonly totalSpent = computed(() => this.spendings().reduce((sum, s) => sum + s.amount, 0));

  addSpending(spending: Omit<Spending, 'id'>): void {
    const newSpending: Spending = {
      ...spending,
      id: crypto.randomUUID(),
    };
    this.spendings.update((current) => [...current, newSpending]);
  }

  deleteSpending(id: string): void {
    this.spendings.update((current) => current.filter((s) => s.id !== id));
  }
}
