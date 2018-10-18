import { Routes } from '@angular/router';
import { NewsListComponent } from './news/list/news.list.component';
import { ErrorComponent } from './error/error.component';

export const appRoutes: Routes = [
  { path: '', redirectTo: 'category/technology', pathMatch: 'full'},
  { path: 'category/:category', component: NewsListComponent},
  { path: 'error', component: ErrorComponent }
];

