import $axiosInst from "@/axios/AxiosInst";

export class rentalService {

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
               console.log(response);
           }).catch( error => {
               console.log(error);
           })
    }

}