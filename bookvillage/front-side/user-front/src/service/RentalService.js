import $axiosInst from "@/axios/AxiosInst";
import { store } from "@/store/index";

export class RentalService {

    constructor(host) {
        this.host = host;
    }

    getRentalList(){
        return $axiosInst
            .get(`${this.host}/rental/list`)
            .then( response => {
                return response.data;
            }).catch( () => {
                console.log( "토큰이 만료되었습니다 .다시 로그인 해주세요.");
                store.commit("logout");
            })
    }

    rentalBook(bookSeq, rentalInfo){
       return $axiosInst
           .post(`${this.host}/rental/book/` + bookSeq , rentalInfo)
           .then( response => {
               return response.data;
           }).catch( error => {
               console.log(error);
           })
    }

    getRentalDetail( rentalSeq ) {
        return $axiosInst
            .get(`${this.host}/rental/detail/` + rentalSeq )
            .then( response => {
                return response.data;
            }).catch( error => {
                console.log(error);
            })
    }

    returnBook( rentalInfo ) {
        return $axiosInst
            .put(`${this.host}/rental/book/return`, rentalInfo )
            .then( response => {
                return response.data;
            }).catch( error => {
                console.log(error);
            })
    }




}