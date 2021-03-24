import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import API_URL from "@/common/config";

class UploadSerivce {

    upload(file) {
        let formData = new FormData();
        formData.append("file", file);

        return axios.post(`${API_URL}/upload`, formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            },
        });
    }

}

export default new UploadSerivce();