import $axiosInst from "@/axios/AxiosInst";

export class rentalService {


    getRentalAllList(){
        return $axiosInst
            .get('/rental/all-list')
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

    getRentalList(){
        return $axiosInst
            .get('/rental/list')
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

    rentalBook(bookSeq, rentalInfo){
       return $axiosInst
           .post('rental/book/' + bookSeq , rentalInfo)
           .then( response => {
               return response.data;
           }).catch( error => {
               return error;
           })
    }

    approveRental(rentalSeq){
        return $axiosInst
            .get('rental/approve/' + rentalSeq)
            .then(response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }
}