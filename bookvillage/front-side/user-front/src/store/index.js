import createPersistedState from 'vuex-persistedstate';
import Vuex from "vuex";
import Vue from 'vue';

Vue.use(Vuex);

// store 생성
export const store = new Vuex.Store({
    plugins: [
        createPersistedState()
    ],
    state: {
        token: null,
    },
    getters: {
        getToken(state){
            return state.token;
        }
    },
    mutations: {  // commit 으로 부를 수 있다.
        setToken(state, payloads){
            state.token = payloads.token;
            alert('로그인 되었습니다.')
        },
        logout(state){
            state.token = null;
            alert('로그아웃 되었습니다.');
        }
    },
    actions: { // dispatch 로 부를 수 있다.
        setToken: (context, payloads ) => {
            context.commit('setToken', payloads);
        },
        logout:(context) => {
            context.commit('logout');
        }
    }
})

