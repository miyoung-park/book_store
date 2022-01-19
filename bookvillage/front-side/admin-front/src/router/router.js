import Vue from 'vue'
import VueRouter from 'vue-router'
import {store} from "@/store";

import Login from "@/views/admin/admin/Login";
import AdminListBooks from "@/views/admin/book/AdminListBooks";
import AdminDetailBook from "@/views/admin/book/AdminDetailBook";
import addBook from '@/views/admin/book/AddBook'
import updateBook from '@/views/admin/book/UpdateBook'
import Admin from "@/components/adminComponent/Admin";
import ListCustomers from "@/views/admin/customer/ListCustomer";
import AddCustomer from "@/views/admin/customer/AddCustomer";
import UpdateCustomer from "@/views/admin/customer/UpdateCustomer";
import AdminListRental from "@/views/admin/rental/AdminListRental";
import AdminDetailCustomer from "@/views/admin/customer/DetailCustomer";
import CustomerPointList from "@/views/admin/point/CustomerPointList";
import AdminDetailRental from "@/views/admin/rental/AdminDetailRental";

// 1. Vue - VueRouter 연결
Vue.use(VueRouter);


function requiredLogin(to, from, next){
    const token = store.getters.getToken;
    if(token == null){
        alert('로그인 후 이용해주세요.');
        next('/admin/login');
    }
    next();
}

function alreadyLogin(to, from, next){
    const token = store.getters.getToken;
    if(token != null){
        next('/admin/book/list');
    }
    next();
}

const routes = [
                 {
                    path: '/',
                    redirect: 'admin/login',
                    component: Admin,
                    children :[
                        {
                            path: 'admin/login',
                            component: Login,
                            beforeEnter :alreadyLogin

                        },
                        {
                            path: 'admin/book/list',
                            component: AdminListBooks,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/book/detail/:bookSeq',
                            component: AdminDetailBook,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/book/add',
                            component: addBook,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/book/update/:bookSeq',
                            component: updateBook,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/customer/list',
                            component: ListCustomers,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/customer/add',
                            component: AddCustomer,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/customer/detail/:userSeq',
                            component: AdminDetailCustomer,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/customer/update/:userSeq',
                            component: UpdateCustomer,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/customer/point/list/:userSeq',
                            component: CustomerPointList,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/rental/list',
                            component: AdminListRental,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/rental/detail/:userSeq',
                            component: AdminDetailRental,
                            beforeEnter: requiredLogin
                        }
                    ]
                 },


                ]

// 3. router 인스턴스 생성
const router = new VueRouter({
    mode: 'history',
    routes
})

// 4. export
export default router;