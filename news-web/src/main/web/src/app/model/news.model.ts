import { ArticleDto } from './article.model';

export class NewsDto {
  country?: string;
  category?: string;
  articles: ArticleDto[] = [];
}
