import { Injectable, Injector, ErrorHandler } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class ErrorHandlerService implements ErrorHandler {
    constructor(private injector: Injector) { }

    handleError(error: any) {
      const router = this.injector.get(Router);
      console.log('URL: ' + router.url);
      if (error instanceof HttpErrorResponse) {
          console.error('Backend returned status code: ', error.status);
          console.error('Response body:', error.message);
      } else {
          console.error('An error occurred:', error.message);
      }
      router.navigate(['/error']);
    }
}
