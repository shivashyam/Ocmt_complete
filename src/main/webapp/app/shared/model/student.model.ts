import { Moment } from 'moment';

export const enum Type {
    PROFESSIONAL = 'PROFESSIONAL',
    CORPORATE = 'CORPORATE',
    INTERNATIONAL = 'INTERNATIONAL',
    DOMESTIC = 'DOMESTIC'
}

export const enum Title {
    MR = 'MR',
    MS = 'MS'
}

export const enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE',
    OTHER = 'OTHER'
}

export const enum Marital {
    SINGLE = 'SINGLE',
    MARRIED = 'MARRIED',
    DIVORCED = 'DIVORCED'
}

export const enum Referral {
    FRIENDS = 'FRIENDS',
    ONLINE = 'ONLINE',
    MEDIA = 'MEDIA'
}

export const enum Whythiscourse {
    CAREER = 'CAREER',
    SECOND = 'SECOND'
}

export const enum Secondary {
    YES = 'YES',
    NO = 'NO'
}

export const enum Univ {
    YES = 'YES',
    NO = 'NO'
}

export const enum Programtype {
    DIPLOMA = 'DIPLOMA',
    CERTIFICATE = 'CERTIFICATE'
}

export const enum Courses {
    CYBERSECURITY = 'CYBERSECURITY',
    SECURITY = 'SECURITY',
    DATA_SCIENCE = 'DATA_SCIENCE',
    AI = 'AI',
    ETHICAL_HACKING = 'ETHICAL_HACKING',
    CYBER_FRAUD = 'CYBER_FRAUD',
    UX = 'UX'
}

export const enum Payments {
    YES = 'YES',
    NO = 'NO'
}

export interface IStudent {
    id?: number;
    studentid?: number;
    fName?: string;
    mName?: string;
    lName?: string;
    uName?: string;
    password?: string;
    type?: Type;
    title?: Title;
    gender?: Gender;
    marital?: Marital;
    dob?: Moment;
    address?: string;
    paddress?: string;
    email?: string;
    phoneNumber?: string;
    referral?: Referral;
    whythiscourse?: Whythiscourse;
    secondary?: Secondary;
    schoolname?: string;
    schoolgraddate?: Moment;
    schooladdress?: string;
    univ?: Univ;
    univname?: string;
    univgraddate?: Moment;
    univaddress?: string;
    sopContentType?: string;
    sop?: any;
    scorecardContentType?: string;
    scorecard?: any;
    programtype?: Programtype;
    courses?: Courses;
    gpa?: number;
    payments?: Payments;
}

export class Student implements IStudent {
    constructor(
        public id?: number,
        public studentid?: number,
        public fName?: string,
        public mName?: string,
        public lName?: string,
        public uName?: string,
        public password?: string,
        public type?: Type,
        public title?: Title,
        public gender?: Gender,
        public marital?: Marital,
        public dob?: Moment,
        public address?: string,
        public paddress?: string,
        public email?: string,
        public phoneNumber?: string,
        public referral?: Referral,
        public whythiscourse?: Whythiscourse,
        public secondary?: Secondary,
        public schoolname?: string,
        public schoolgraddate?: Moment,
        public schooladdress?: string,
        public univ?: Univ,
        public univname?: string,
        public univgraddate?: Moment,
        public univaddress?: string,
        public sopContentType?: string,
        public sop?: any,
        public scorecardContentType?: string,
        public scorecard?: any,
        public programtype?: Programtype,
        public courses?: Courses,
        public gpa?: number,
        public payments?: Payments
    ) {}
}
