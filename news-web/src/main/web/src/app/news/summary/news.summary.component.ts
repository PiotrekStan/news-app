import { Component, OnInit, Input } from '@angular/core';
import { NewsService } from '../../service/news.service';
import { ArticleDto } from '../../model/article.model';
import { DomSanitizer } from '@angular/platform-browser';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

const NO_PICTURE_PATH = '/assets/nopicture.jpg';

@Component({
  selector: 'app-news-summary',
  templateUrl: './news.summary.component.html',
  styleUrls: ['./news.summary.component.css'],
})

export class NewsSummaryComponent implements OnInit {

  @Input()
  article: ArticleDto;

  private articlePicture;

  constructor(private sanitizer: DomSanitizer, private modalService: NgbModal) { }
  news: any = [];

  ngOnInit(): void {
    if (!this.article.imageUrl) {
      this.article.imageUrl = NO_PICTURE_PATH;
    }
    this.articlePicture = this.sanitizer.bypassSecurityTrustStyle('url(' + this.article.imageUrl + ')');
  }

  openModal(content) {
    this.modalService.open(content, { size: 'lg' });
  }

}
