import $axiosInst from "@/axios/AxiosInst";
import {adminResponseApi} from "@/util/AdminResponseApi";

export class bookService {

    constructor(host) {
        this.host = host;
    }

    addBook(bookObj , bookImages){
        let formData = new FormData();  // TODO: for 문으로 변환해보기 key 값 뽑아서 ! (완)
        for( let key in bookObj ){
            formData.append(key ,bookObj[key])
        }
        if(bookImages != null){
            for( let key in bookImages ){
                formData.append('files' ,bookImages[key])
            }
        }
        return $axiosInst
            .post(`${this.host}/book/add/`, formData )
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    getBookList(){
        return $axiosInst
            .get(`${this.host}/book/list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    getBookDetail(bookSeq){
        return $axiosInst
            .get(`${this.host}/book/detail/` + bookSeq)
            .then( response => {
                return response.data;
            }).catch(error => {
                throw adminResponseApi(error);
            })
    }

    updateBook(bookObj , newImages , deleteFiles) {
        const formData = new FormData();
        for( let key in bookObj ){
            formData.append(key ,bookObj[key])
        }

        if(newImages != null){
            for(let i = 0 ; i < newImages.length; i ++){
                formData.append('files' , newImages[i]);
            }
        }
        if(deleteFiles != null){
            for(let i = 0; i < deleteFiles.length; i++){
                formData.append('deleteFiles' , deleteFiles[i])
            }
        }
        return $axiosInst
            .put(`${this.host}/book/update/` + bookObj.bookSeq, formData )
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })

    }

    deleteBook(bookSeq){
        return $axiosInst
            .delete(`${this.host}/book/delete/` + bookSeq)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }



}