import Vue from 'vue'
import VueRouter from 'vue-router'
import { store } from '@/store/index'
/* basic */
import ListBooks from "@/views/user/book/ListBooks";
import detailBook from '@/views/user/book/DetailBook'
import Basic from "@/components/Basic";
import Login from "@/views/user/customer/login/Login";
/* admin */
import AdminListBooks from "@/views/admin/book/AdminListBooks";
import AdminDetailBook from "@/views/admin/book/AdminDetailBook";
import addBook from '@/views/admin/book/AddBook'
import updateBook from '@/views/admin/book/UpdateBook'
import Admin from "@/components/admin/Admin";
import DetailCustomer from "@/views/user/customer/profile/DetailCustomer";
import ListRentals from "@/views/user/customer/rental/ListRental";
import ListCustomers from "@/views/admin/customer/ListCustomers";
import AddCustomer from "@/views/admin/customer/AddCustomer";
import UpdateCustomer from "@/views/admin/customer/UpdateCustomer";
import AdminListRental from "@/views/admin/rental/AdminListRental";
import PointList from "@/views/user/customer/point/PointList";
import DetailRental from "@/views/user/customer/rental/DetailRental";
import AdminDetailCustomer from "@/views/admin/customer/AdminDetailCustomer";

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
        next({path: '/'})
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
                    redirect: 'book/list',
                    component: Basic,
                    children: [
                        {
                            path: 'login',
                            component: Login
                        },
                        {
                            path: 'book/list',
                            component: ListBooks
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: detailBook
                        },
                        {
                            path: 'customer/detail/',
                            component: DetailCustomer
                        },
                        {
                            path: 'customer/point/list',
                            component: PointList
                        },
                        {
                            path: 'customer/rental/list',
                            component: ListRentals
                        },
                        {
                            path: 'customer/rental/detail/:rentalSeq',
                            component: DetailRental
                        }
                    ]
                },
                 {
                    path: '/admin',
                     redirect: 'admin/book/list',
                    component: Admin,
                    beforeEnter: checkAdminRight,
                    children :[
                        {
                            path: 'book/list',
                            component: AdminListBooks
                        },
                        {
                            path: 'book/add',
                            component: addBook
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: AdminDetailBook
                        },
                        {
                            path: 'book/update/:bookSeq',
                            component: updateBook
                        },
                        {
                            path: 'customer/list',
                            component: ListCustomers
                        },
                        {
                            path: 'customer/add',
                            component: AddCustomer
                        },
                        {
                            path: 'customer/detail/:userSeq',
                            component: AdminDetailCustomer
                        },
                        {
                            path: 'customer/update/:userSeq',
                            component: UpdateCustomer
                        },
                        {
                            path: 'rental/list',
                            component: AdminListRental
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