import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStudent } from 'app/shared/model/student.model';

type EntityResponseType = HttpResponse<IStudent>;
type EntityArrayResponseType = HttpResponse<IStudent[]>;

@Injectable({ providedIn: 'root' })
export class StudentService {
    private resourceUrl = SERVER_API_URL + 'api/students';

    constructor(private http: HttpClient) {}

    create(student: IStudent): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(student);
        return this.http
            .post<IStudent>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(student: IStudent): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(student);
        return this.http
            .put<IStudent>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStudent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStudent[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(student: IStudent): IStudent {
        const copy: IStudent = Object.assign({}, student, {
            dob: student.dob != null && student.dob.isValid() ? student.dob.format(DATE_FORMAT) : null,
            schoolgraddate:
                student.schoolgraddate != null && student.schoolgraddate.isValid() ? student.schoolgraddate.format(DATE_FORMAT) : null,
            univgraddate: student.univgraddate != null && student.univgraddate.isValid() ? student.univgraddate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
        res.body.schoolgraddate = res.body.schoolgraddate != null ? moment(res.body.schoolgraddate) : null;
        res.body.univgraddate = res.body.univgraddate != null ? moment(res.body.univgraddate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((student: IStudent) => {
            student.dob = student.dob != null ? moment(student.dob) : null;
            student.schoolgraddate = student.schoolgraddate != null ? moment(student.schoolgraddate) : null;
            student.univgraddate = student.univgraddate != null ? moment(student.univgraddate) : null;
        });
        return res;
    }
}
