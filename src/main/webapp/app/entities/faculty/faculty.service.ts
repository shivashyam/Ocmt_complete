import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFaculty } from 'app/shared/model/faculty.model';

type EntityResponseType = HttpResponse<IFaculty>;
type EntityArrayResponseType = HttpResponse<IFaculty[]>;

@Injectable({ providedIn: 'root' })
export class FacultyService {
    private resourceUrl = SERVER_API_URL + 'api/faculties';

    constructor(private http: HttpClient) {}

    create(faculty: IFaculty): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(faculty);
        return this.http
            .post<IFaculty>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(faculty: IFaculty): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(faculty);
        return this.http
            .put<IFaculty>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFaculty>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFaculty[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(faculty: IFaculty): IFaculty {
        const copy: IFaculty = Object.assign({}, faculty, {
            dob: faculty.dob != null && faculty.dob.isValid() ? faculty.dob.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((faculty: IFaculty) => {
            faculty.dob = faculty.dob != null ? moment(faculty.dob) : null;
        });
        return res;
    }
}
