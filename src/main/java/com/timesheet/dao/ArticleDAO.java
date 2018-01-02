package com.timesheet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.entity.Article;

@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Article> getAllArticles() {
			String hql = "FROM Article as article ORDER BY article.articleId";
			return (List<Article>)entityManager.createQuery(hql).getResultList();
	}

	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class,articleId);
	}

	public void addArticle(Article article) {
		entityManager.persist(article);
	}

	public void updateArticle(Article article) {
		Article art = getArticleById(article.getArticleId());
		art.setTitle(article.getTitle());
		art.setCategory(article.getCategory());
		entityManager.flush();
	}

	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}

	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as article where art.title=? and art.category=?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
					.setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}

}
