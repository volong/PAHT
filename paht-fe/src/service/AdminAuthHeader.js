export default function adminAuthHeader() {
    let admin = JSON.parse(localStorage.getItem('admin'));

    if (admin && admin.accessToken) {
        return { Authorization: 'Bearer ' + admin.accessToken }
    } else {
        return {};
    }
}