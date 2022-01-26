import $axiosInst from "@/axios/AxiosInst";

export class RentalService {

    constructor(host) {
        this.host = host;
    }

    getRentalAllList(){
        return $axiosInst
            .get(`${this.host}/rental/all-list`)
            .then( response => {
                return response.data;
            })
    }

    getRentalListBySeq(userSeq){
        return $axiosInst
            .get(`${this.host}/rental/list/` + userSeq)
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

    approveRental(rentalSeq){
        return $axiosInst
            .put(`${this.host}/rental/approve/`+ rentalSeq
            ).then(response => {
                return response.data;
            })
    }

    rejectRental(rentalSeq){
        return $axiosInst
            .put(`${this.host}/rental/reject/`+ rentalSeq
            ).then( response => {
                return response.data;
            })
    }
}