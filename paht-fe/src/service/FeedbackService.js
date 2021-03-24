import BaseService from "./baseService";

class FeedbackService {

    findByArticleId(article_id) {
        return BaseService.get(`feedback/byarticle/${article_id}`)
    }

    create(params) {
        return BaseService.post(`feedback`, params)
    }

    update(formData) {
        return BaseService.put(`feedback`, formData)
    }

}
export default new FeedbackService();