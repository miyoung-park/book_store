import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store/index'
import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { customerService } from "@/service/customerService";
import { bookService } from "@/service/bookService";
import { pointService} from "@/service/pointService";
import { rentalService} from "@/service/rentalService";

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$axiosInst = axiosInst

const API_URL_HOST = process.env.VUE_APP_API_HOST;

new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  provide: {
    'bookService' : new bookService(API_URL_HOST),
    'customerService' : new customerService(API_URL_HOST),
    'pointService' : new pointService(API_URL_HOST),
    'rentalService' : new rentalService(API_URL_HOST)
  },
}).$mount('#app')