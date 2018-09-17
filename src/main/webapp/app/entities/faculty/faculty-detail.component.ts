import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFaculty } from 'app/shared/model/faculty.model';

@Component({
    selector: 'jhi-faculty-detail',
    templateUrl: './faculty-detail.component.html'
})
export class FacultyDetailComponent implements OnInit {
    faculty: IFaculty;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ faculty }) => {
            this.faculty = faculty;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
