import { Moment } from 'moment';

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

export interface IStaff {
    id?: number;
    stafftid?: number;
    fName?: string;
    mName?: string;
    lName?: string;
    uName?: string;
    password?: string;
    title?: Title;
    gender?: Gender;
    marital?: Marital;
    dob?: Moment;
    address?: string;
    paddress?: string;
    email?: string;
    phoneNumber?: string;
    cert1ContentType?: string;
    cert1?: any;
    cert2ContentType?: string;
    cert2?: any;
}

export class Staff implements IStaff {
    constructor(
        public id?: number,
        public stafftid?: number,
        public fName?: string,
        public mName?: string,
        public lName?: string,
        public uName?: string,
        public password?: string,
        public title?: Title,
        public gender?: Gender,
        public marital?: Marital,
        public dob?: Moment,
        public address?: string,
        public paddress?: string,
        public email?: string,
        public phoneNumber?: string,
        public cert1ContentType?: string,
        public cert1?: any,
        public cert2ContentType?: string,
        public cert2?: any
    ) {}
}
