import { Component, inject } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { SpendingService } from '../spending.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CurrencyPipe, RouterLink],
  templateUrl: './landing.html',
  styleUrl: './landing.scss',
})
export class LandingComponent {
  private readonly spendingService = inject(SpendingService);
  readonly spendings = this.spendingService.allSpendings;
  readonly totalSpent = this.spendingService.totalSpent;

  deleteSpending(id: string): void {
    this.spendingService.deleteSpending(id);
  }
}
