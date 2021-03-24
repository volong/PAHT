import axios from 'axios';
import API_URL from "../common/config";

class AdminAuthService {
    login(admin) {
        return axios.post(API_URL + '/signin/admin', {
            username: admin.username,
            password: admin.password
        })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('admin', JSON.stringify(response.data));
                }
                return response.data;
            })
    }

    logout() {
        localStorage.removeItem('admin')
    }
}

export default new AdminAuthService();