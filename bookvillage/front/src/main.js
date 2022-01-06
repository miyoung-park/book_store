import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store/index'
import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { adminService } from '@/service/adminService'
import { customerService } from "@/service/customerService";
import { bookService } from "@/service/bookService";
import { pointService} from "@/service/pointService";


Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$axiosInst = axiosInst
new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  provide: {
    'adminService': new adminService,
    'bookService' : new bookService,
    'customerService' : new customerService,
    'pointService' : new pointService
  },
}).$mount('#app')
