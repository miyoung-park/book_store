import $axiosInst from "@/axios/AxiosInst";

export class BookService {

    constructor(host) {
        this.host = host;
    }

    getBookList(){
        return $axiosInst
            .get(`${this.host}/book/list`)
            .then( response => {
                return response.data;
            })
    }

    getBookDetail(bookSeq){
        return $axiosInst
            .get(`${this.host}/book/detail/` + bookSeq)
            .then( response => {
                return response.data;
            })
    }


}