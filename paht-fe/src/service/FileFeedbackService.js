import BaseService from "./baseService";

class FileFeedbackService {
    findByFeedbackId(fb_id) {
        return BaseService.get(`files_feedback/${fb_id}`)
    }

    create(params = []) {
        return BaseService.post("file_feedback", params);
    }
}
export default new FileFeedbackService()