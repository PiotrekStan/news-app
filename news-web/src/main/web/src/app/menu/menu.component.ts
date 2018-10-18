import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {
    constructor(private modalService: NgbModal) { }

    ngOnInit(): void { }

    openModal(content) {
        this.modalService.open(content, { size: 'lg' });
      }
}
