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
            .delete('/book/delete/' + bookSeq)
            .then( response => {
                return response;
            }).catch( error => {
                return error;
            })
    }

    addBook(bookObj , bookImages){
        const formData = new FormData();
        formData.append('bookObj', bookObj.bookTitle);
        formData.append('bookTitle' , bookObj.bookTitle);
        formData.append('bookPrice',bookObj.bookTitle);
        formData.append('bookMemo', bookObj.bookTitle);
        if(bookImages != null){
            for(let i = 0 ; i < bookImages.length; i ++){
                formData.append('files' , bookImages[i]);
            }
        }
        return $axiosInst
            .post('/book/add/', formData )
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

}