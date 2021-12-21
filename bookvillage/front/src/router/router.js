import Vue from 'vue'
import VueRouter from 'vue-router'
import { store } from '@/store/index'
import ListBooks from "@/views/user/book/ListBooks";
import detailBook from '@/views/user/book/DetailBook'
import addBook from '@/views/admin/book/AddBook'
import updateBook from '@/views/admin/book/UpdateBook'
import Admin from "@/components/user/admin/Admin";
import Customer from "@/components/user/customer/Customer";
import Login from "@/views/user/login/Login";
//import ListRentals from "@/views/customer/rental/ListRentals";//
//import Main from "@/views/Main";
// 1. Vue - VueRouter 연결
Vue.use(VueRouter);

/*
const bookProps = (route) => {
    const props =  {}
    props.query = route.query;
    return props;
}; */

const role = store.getters.getRole;

function checkAdminRight(to , from, next){
    if(role != 'admin'){
        alert('접근할 수 없습니다.')
        next({path: '/main'})
    }
    next();
}
/*
function checkCustomerRight(to , from, next){
    if(role != 'customer'){
        alert('접근할 수 없습니다.')
        next({path: '/main'})
    }
    next();
} */

const routes = [
                {
                    path: '/',
                    component: Customer,
                    children: [
                        {
                            path: '/login',
                            component: Login
                        },
                    ]
                },
                 {
                    path: '/admin',
                    component: Admin,
                    beforeEnter: checkAdminRight,
                    children :[
                        {
                            path: 'book/list',
                            component: ListBooks
                        },
                        {
                            path: 'book/add',
                            component: addBook
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: detailBook
                        },
                        {
                            path: 'book/update/:bookSeq',
                            component: updateBook
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