import $axiosInst from "@/axios/AxiosInst";

export class RentalService {

    constructor(host) {
        this.host = host;
    }

    getRentalList(){
        return $axiosInst
            .get(`${this.host}/rental/list`)
            .then( response => {
                return response.data;
            })
    }

    rentalBook(bookSeq, rentalInfo){
       return $axiosInst
           .post(`${this.host}/rental/book/` + bookSeq , rentalInfo)
           .then( response => {
               return response.data;
           })
    }

    getRentalDetail( rentalSeq ) {
        return $axiosInst
            .get(`${this.host}/rental/detail/` + rentalSeq )
            .then( response => {
                return response.data;
            })
    }

    returnBook( rentalInfo ) {
        return $axiosInst
            .put(`${this.host}/rental/book/return`, rentalInfo )
            .then( response => {
                return response.data;
            })
    }




}