import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OcmtSharedModule } from 'app/shared';
import {
    StaffComponent,
    StaffDetailComponent,
    StaffUpdateComponent,
    StaffDeletePopupComponent,
    StaffDeleteDialogComponent,
    staffRoute,
    staffPopupRoute
} from './';

const ENTITY_STATES = [...staffRoute, ...staffPopupRoute];

@NgModule({
    imports: [OcmtSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [StaffComponent, StaffDetailComponent, StaffUpdateComponent, StaffDeleteDialogComponent, StaffDeletePopupComponent],
    entryComponents: [StaffComponent, StaffUpdateComponent, StaffDeleteDialogComponent, StaffDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OcmtStaffModule {}
