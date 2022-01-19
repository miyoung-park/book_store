import $router from "@/router/router";
import {store} from "@/store";


/**
 * User Response
 * @param error
 * @returns {Promise<Route>|*}
 */
export function responseApi( error ) {

    const status = error.response.status;
    const errorMsg = error.response.data.errorMsg;

    if( status === 401 ) {
        alert(errorMsg);
        store.dispatch('logout');
        $router.push('/login');
    }

    if( status === 403 ) {
        alert(errorMsg);
        location.reload();
    }
   throw error;
}