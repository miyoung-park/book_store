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

}