import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SpendingService } from '../spending.service';

@Component({
  selector: 'app-add-spending',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-spending.html',
  styleUrl: './add-spending.scss',
})
export class AddSpendingComponent {
  private readonly spendingService = inject(SpendingService);
  private readonly router = inject(Router);

  title = signal('');
  amount = signal<number>(0);
  category = signal('Food');
  date = signal(new Date().toISOString().split('T')[0]);

  readonly categories = ['Food', 'Transport', 'Entertainment', 'Utilities', 'Shopping', 'Other'];

  addSpending(): void {
    if (!this.title() || this.amount() <= 0) {
      return;
    }

    this.spendingService.addSpending({
      title: this.title(),
      amount: this.amount(),
      category: this.category(),
      date: this.date(),
    });

    this.router.navigate(['/']);
  }
}
