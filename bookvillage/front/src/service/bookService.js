import $axiosInst from "@/axios/AxiosInst";

export class bookService {

    getBookDetail(bookSeq){
        return $axiosInst
            .get('/book/detail/' + bookSeq)
            .then( response => {
                return response;
            }).catch(error => {
                return error;
            })
    }

    deleteBook(bookSeq){
        return $axiosInst
            .delete('/admin/book/delete/' + bookSeq)
            .then( response => {
                return response;
            }).catch( error => {
                return error;
            })
    }

}