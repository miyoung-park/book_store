import createPersistedState from 'vuex-persistedstate';
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [createPersistedState()]
    ,
    state: {
        token: null,
        authorization: null
    },
    getters: {

    },
    mutations: {

    },
    actions: {

    }

})