import $axiosInst from "@/axios/AxiosInst";
import {adminResponseApi} from "@/util/AdminResponseApi";

export class rentalService {

    constructor(host) {
        this.host = host;
    }

    getRentalAllList(){
        return $axiosInst
            .get(`${this.host}/rental/all-list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    getRentalListBySeq(userSeq){
        return $axiosInst
            .get(`${this.host}/rental/list/` + userSeq)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    rentalBook(bookSeq, rentalInfo){
        return $axiosInst
            .post(`${this.host}/rental/book/` + bookSeq , rentalInfo)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    approveRental(rentalSeq){
        return $axiosInst
            .put(`${this.host}/rental/approve/`+ rentalSeq
            ).then(response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    rejectRental(rentalSeq){
        return $axiosInst
            .put(`${this.host}/rental/reject/`+ rentalSeq
            ).then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }
}