import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../service/news.service';
import { NewsDto } from '../../model/news.model';
import { ActivatedRoute, ParamMap} from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-news-list',
  templateUrl: './news.list.component.html',
})
export class NewsListComponent implements OnInit {
  constructor(public service: NewsService, private route: ActivatedRoute) { }
  news: NewsDto;
  category: string;

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.category = params.get('category');
      this.getNews(this.category);
    });
  }

  getNews(category: string) {
    this.service.getByCountryAndCategory('pl', category)
      .subscribe(
        (data) => { this.news = data;
                    console.log(this.news); },
        (error) => { throw error; }
        );
  }
}
