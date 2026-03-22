import { Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { AddSpendingComponent } from './add-spending/add-spending.component';

export const routes: Routes = [
    { path: '', component: LandingComponent },
    { path: 'add-spending', component: AddSpendingComponent }
];
