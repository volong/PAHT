import AdminAuthService from "../service/AdminAuthService";
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const admin = JSON.parse(localStorage.getItem('admin'))

const initialState = admin
    ? { status: { loggedIn: true }, admin }
    : { status: { loggedIn: false }, admin: null };


export const adminAuth = {
    namespaced: true,
    state:
        initialState,
    actions: {
        login({ commit }, admin) {
            return AdminAuthService.login(admin).then(
                admin => {
                    commit('loginSuccess', admin);
                    return Promise.resolve(admin);
                },

                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            )
        },

        logout({ commit }) {
            AdminAuthService.logout();
            commit('logout')
        },

    },
    mutations: {
        loginSuccess(state, admin) {
            state.status.loggedIn = true;
            state.admin = admin;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.admin = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.admin = null;
        },
    }
}