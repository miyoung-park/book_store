import $axiosInst from "@/axios/AxiosInst";

export class bookService {

    getBookDetail(bookSeq){
        return $axiosInst
            .get('/book/detail/' + bookSeq)
            .then( response => {
                return response.data;
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
        console.log(bookObj)
        formData.append('bookTitle' , bookObj.bookTitle);
        formData.append('bookPrice',bookObj.bookPrice);
        formData.append('bookRentalFee',bookObj.bookRentalFee);
        formData.append('bookMemo', bookObj.bookMemo);
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