import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFaculty } from 'app/shared/model/faculty.model';
import { Principal } from 'app/core';
import { FacultyService } from './faculty.service';

@Component({
    selector: 'jhi-faculty',
    templateUrl: './faculty.component.html'
})
export class FacultyComponent implements OnInit, OnDestroy {
    faculties: IFaculty[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private facultyService: FacultyService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.facultyService.query().subscribe(
            (res: HttpResponse<IFaculty[]>) => {
                this.faculties = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFaculties();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFaculty) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInFaculties() {
        this.eventSubscriber = this.eventManager.subscribe('facultyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
