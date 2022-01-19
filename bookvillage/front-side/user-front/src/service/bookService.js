import $axiosInst from "@/axios/AxiosInst";
import {responseApi} from "@/util/responseApi";

export class bookService {

    constructor(host) {
        this.host = host;
    }

    getBookList(){
        return $axiosInst
            .get(`${this.host}/book/list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw responseApi(error.response);
            })
    }

    getBookDetail(bookSeq){
        return $axiosInst
            .get(`${this.host}/book/detail/` + bookSeq)
            .then( response => {
                return response.data;
            }).catch(error => {
                throw responseApi(error.response);
            })
    }


}