import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { NewsSummaryComponent } from './news/summary/news.summary.component';
import { HttpClientModule } from '@angular/common/http';
import { NewsService } from './service/news.service';
import { NewsListComponent } from './news/list/news.list.component';
import { RouterModule } from '@angular/router';
import { appRoutes } from './routes';
import { ErrorHandlerService } from './error/error.handler.service';
import { ErrorComponent } from './error/error.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    NewsSummaryComponent,
    NewsListComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule
  ],
  providers: [NewsService, ErrorHandlerService,
    { provide: ErrorHandler, useClass: ErrorHandlerService }],
  bootstrap: [AppComponent, MenuComponent]
})
export class AppModule { }
