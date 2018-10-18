import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { NewsDto } from '../model/news.model';

const endpoint = 'http://localhost:8080/rest/api/news/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class NewsService {

  constructor(private http: HttpClient) {
  }

  getByCountryAndCategory(country: string, category: string): Observable<any> {
    return this.http.get<NewsDto>(endpoint + country + '/' + category);
  }

}
