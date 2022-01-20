import $axiosInst from "@/axios/AxiosInst";
import {responseApi} from "@/util/responseApi";

export class rentalService {

    constructor(host) {
        this.host = host;
    }

    getRentalList(){
        return $axiosInst
            .get(`${this.host}/rental/list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw responseApi(error);
            })
    }

    rentalBook(bookSeq, rentalInfo){
       return $axiosInst
           .post(`${this.host}/rental/book/` + bookSeq , rentalInfo)
           .then( response => {
               return response.data.data;
           }).catch( error => {
               throw responseApi(error);
           })
    }

    getRentalDetail( rentalSeq ) {
        return $axiosInst
            .get(`${this.host}/rental/detail/` + rentalSeq )
            .then( response => {
                return response.data.responseBodyMap;
            }).catch( error => {
                throw responseApi(error);
            })
    }

    returnBook( rentalInfo ) {
        return $axiosInst
            .put(`${this.host}/rental/book/return`, rentalInfo )
            .then( response => {
                console.log(response);
                return response.data.data;
            }).catch( error => {
                throw responseApi(error);
            })
    }




}