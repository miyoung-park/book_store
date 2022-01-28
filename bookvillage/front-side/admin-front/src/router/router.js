import Vue from 'vue'
import VueRouter from 'vue-router'
import {store} from "@/store";

import login from "@/views/admin/admin/Login";
import adminListBooks from "@/views/admin/book/AdminListBooks";
import adminDetailBook from "@/views/admin/book/AdminDetailBook";
import addBook from '@/views/admin/book/AddBook'
import updateBook from '@/views/admin/book/UpdateBook'
import admin from "@/components/adminComponent/Admin";
import listUsers from "@/views/admin/user/ListUser";
import AddUser from "@/views/admin/user/AddUser";
import updateUser from "@/views/admin/user/UpdateUser";
import adminListRental from "@/views/admin/rental/AdminListRental";
import adminDetailUser from "@/views/admin/user/DetailUser";
import userPointList from "@/views/admin/point/UserPointList";
import adminDetailRental from "@/views/admin/rental/AdminDetailRental";

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
                    component: admin,
                    children :[
                        {
                            path: 'admin/login',
                            component: login,
                            beforeEnter :alreadyLogin

                        },
                        {
                            path: 'admin/book/list',
                            component: adminListBooks,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/book/detail/:bookSeq',
                            component: adminDetailBook,
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
                            path: 'admin/user/list',
                            component: listUsers,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/user/add',
                            component: AddUser,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/user/detail/:userSeq',
                            component: adminDetailUser,
                            beforeEnter: requiredLogin

                        },
                        {
                            path: 'admin/user/update/:userSeq',
                            component: updateUser,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/user/point/list/:userSeq',
                            component: userPointList,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/rental/list',
                            component: adminListRental,
                            beforeEnter: requiredLogin
                        },
                        {
                            path: 'admin/rental/detail/:userSeq',
                            component: adminDetailRental,
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