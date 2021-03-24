import BaseService from "./baseService";

class FileArticleService {
    findByArticleId(article_id) {
        return BaseService.get(`files_article/${article_id}`)
    }

    create(params = []) {
        return BaseService.post("files_article", params);
    }
}
export default new FileArticleService()