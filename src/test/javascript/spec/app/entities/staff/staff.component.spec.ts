/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { OcmtTestModule } from '../../../test.module';
import { StaffComponent } from 'app/entities/staff/staff.component';
import { StaffService } from 'app/entities/staff/staff.service';
import { Staff } from 'app/shared/model/staff.model';

describe('Component Tests', () => {
    describe('Staff Management Component', () => {
        let comp: StaffComponent;
        let fixture: ComponentFixture<StaffComponent>;
        let service: StaffService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OcmtTestModule],
                declarations: [StaffComponent],
                providers: []
            })
                .overrideTemplate(StaffComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StaffComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StaffService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Staff(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.staff[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
