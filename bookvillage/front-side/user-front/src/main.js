import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store'
import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { UserService } from "@/service/UserService";
import {BookService} from "@/service/BookService";
import { PointService} from "@/service/PointService";
import { RentalService} from "@/service/RentalService";
import ApiServices from "@/plugins/api-service-plugin";
import ErrorCode from "@/service/enum/enumErrorCode";
import dateFormat from "@/service/format/dateFormat";

const API_URL_HOST = process.env.VUE_APP_API_HOST;

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$axiosInst = axiosInst
Vue.prototype.$errorCode = ErrorCode // ErrorCode Class
/** use service plugin **/
Vue.use( ApiServices, {host:API_URL_HOST} )

Vue.mixin({
  beforeDestroy(){
    if (this.$apiErrorHandler) {
      this.$removeApiErrorHandler( this.$apiErrorHandler );
    }
  }
})

new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  dateFormat,
  provide: {
    'bookServiceUser' : new BookService(API_URL_HOST),
    'userService' : new UserService(API_URL_HOST),
    'pointService' : new PointService(API_URL_HOST),
    'rentalService' : new RentalService(API_URL_HOST)
  },
}).$mount('#app')