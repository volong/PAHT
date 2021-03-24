import axios from 'axios';
import API_URL from '../common/config';
import authHeader from './AuthHeader';

class UserService {
    getPublicContent() {
    }

    getUserBoard() {
        return axios.get(API_URL + '/user', { headers: authHeader() })
    }

    getDepartmenterBoard() {
        return axios.get(API_URL + '/dep', { headers: authHeader() })
    }

    getAdminBoard() {
        return axios.get(API_URL + '/admin', { headers: authHeader() })

    }
}

export default new UserService();