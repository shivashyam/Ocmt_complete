import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OcmtSharedModule } from 'app/shared';
import {
    FacultyComponent,
    FacultyDetailComponent,
    FacultyUpdateComponent,
    FacultyDeletePopupComponent,
    FacultyDeleteDialogComponent,
    facultyRoute,
    facultyPopupRoute
} from './';

const ENTITY_STATES = [...facultyRoute, ...facultyPopupRoute];

@NgModule({
    imports: [OcmtSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FacultyComponent,
        FacultyDetailComponent,
        FacultyUpdateComponent,
        FacultyDeleteDialogComponent,
        FacultyDeletePopupComponent
    ],
    entryComponents: [FacultyComponent, FacultyUpdateComponent, FacultyDeleteDialogComponent, FacultyDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OcmtFacultyModule {}
