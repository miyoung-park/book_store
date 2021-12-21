import Vue from 'vue'
import VueRouter from 'vue-router'
import { store } from '@/store/index'
import ListBooks from "@/views/book/ListBooks";
import Admin from "@/components/user/admin/Admin";
import Customer from "@/components/user/customer/Customer";
import Login from "@/views/common/login/Login";
import DetailBook from "@/views/book/DetailBook";
import ListRentals from "@/views/rental/ListRentals";//
import Main from "@/views/Main";
// 1. Vue - VueRouter 연결
Vue.use(VueRouter);

/*
const bookProps = (route) => {
    const props =  {}
    props.query = route.query;
    return props;
}; */

function checkAdminRight(to , from, next){
    const role = store.getters.getRole;
    if(role != 'admin'){
        alert('접근할 수 없습니다.')
        next({path: '/main'})
    }
    next();
}


const routes = [
                {
                    path:'/',
                    component: Main,
                    children:[
                        {
                            path: 'main',
                            component: ListBooks
                        },
                        {
                            path: 'list/book',
                            component: ListBooks
                        },
                        {
                            path: 'login',
                            component: Login
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: DetailBook
                        },
                        {
                            path: '/admin',
                            component: Admin,
                            beforeEnter: checkAdminRight,
                            children :[
                                {
                                    path: 'list/books',
                                    component: ListBooks
                                }
                            ]
                        },
                        {
                            path: '/customer',
                            component: Customer,
                            children: [
                                {
                                    path: '/list/rentals',
                                    component: ListRentals
                                }
                            ]
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