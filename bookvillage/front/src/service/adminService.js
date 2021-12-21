import $axiosInst from "@/axios/AxiosInst";

export class adminService {

    adminLogin(userObj) {
        return $axiosInst
                .post('/admin/login', userObj)
                .then(response => {
                   return response.data;
                }).catch(error => {
                    return error;
            })
    }

}
