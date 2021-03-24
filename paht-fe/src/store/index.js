import Vuex from 'vuex';
import Vue from 'vue';

import { userAuth } from './userAuth.module';
import { adminAuth } from './adminAuth.module';
import { depAuth } from './depAuth.module';



Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        userAuth,
        adminAuth,
        depAuth
    }
})