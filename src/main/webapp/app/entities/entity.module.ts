import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { OcmtStudentModule } from './student/student.module';
import { OcmtFacultyModule } from './faculty/faculty.module';
import { OcmtStaffModule } from './staff/staff.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        OcmtStudentModule,
        OcmtFacultyModule,
        OcmtStaffModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OcmtEntityModule {}
