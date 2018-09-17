import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { IFaculty } from 'app/shared/model/faculty.model';
import { FacultyService } from './faculty.service';

@Component({
    selector: 'jhi-faculty-update',
    templateUrl: './faculty-update.component.html'
})
export class FacultyUpdateComponent implements OnInit {
    private _faculty: IFaculty;
    isSaving: boolean;
    dobDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private facultyService: FacultyService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
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

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.faculty, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.faculty.id !== undefined) {
            this.subscribeToSaveResponse(this.facultyService.update(this.faculty));
        } else {
            this.subscribeToSaveResponse(this.facultyService.create(this.faculty));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFaculty>>) {
        result.subscribe((res: HttpResponse<IFaculty>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get faculty() {
        return this._faculty;
    }

    set faculty(faculty: IFaculty) {
        this._faculty = faculty;
    }
}
