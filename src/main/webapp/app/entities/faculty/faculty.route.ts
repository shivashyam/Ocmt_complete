import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Faculty } from 'app/shared/model/faculty.model';
import { FacultyService } from './faculty.service';
import { FacultyComponent } from './faculty.component';
import { FacultyDetailComponent } from './faculty-detail.component';
import { FacultyUpdateComponent } from './faculty-update.component';
import { FacultyDeletePopupComponent } from './faculty-delete-dialog.component';
import { IFaculty } from 'app/shared/model/faculty.model';

@Injectable({ providedIn: 'root' })
export class FacultyResolve implements Resolve<IFaculty> {
    constructor(private service: FacultyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((faculty: HttpResponse<Faculty>) => faculty.body));
        }
        return of(new Faculty());
    }
}

export const facultyRoute: Routes = [
    {
        path: 'faculty',
        component: FacultyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ocmtApp.faculty.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'faculty/:id/view',
        component: FacultyDetailComponent,
        resolve: {
            faculty: FacultyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ocmtApp.faculty.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'faculty/new',
        component: FacultyUpdateComponent,
        resolve: {
            faculty: FacultyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ocmtApp.faculty.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'faculty/:id/edit',
        component: FacultyUpdateComponent,
        resolve: {
            faculty: FacultyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ocmtApp.faculty.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const facultyPopupRoute: Routes = [
    {
        path: 'faculty/:id/delete',
        component: FacultyDeletePopupComponent,
        resolve: {
            faculty: FacultyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ocmtApp.faculty.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
