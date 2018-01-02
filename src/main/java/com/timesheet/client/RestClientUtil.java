package com.timesheet.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.timesheet.entity.Article;

public class RestClientUtil {
	
	public void getArticleByDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article/{id}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Article> responseEntity = restTemplate.exchange(url,HttpMethod.GET, requestEntity,Article.class,1);
		Article article = responseEntity.getBody();
		System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()+", Category:"+article.getCategory());
	}
	
	public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/articles";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Article[].class);
		Article[] articles = responseEntity.getBody();
		for(Article article : articles) {
			System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()+", Category:"+article.getCategory());
		}
	}
	
	public void addArticleDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article";
		Article objArticle = new Article();
		objArticle.setTitle("SPRING REST SECURITY USING HIBERNATE");
		objArticle.setCategory("Spring");
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle,headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath()); 
	}
	
	public void updateArticleDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article";
		Article objArticle = new Article();
		objArticle.setArticleId(1);
		objArticle.setTitle("Update: java currency");
		objArticle.setCategory("java");
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle,headers);
		restTemplate.put(url, requestEntity);
	}
	
	public void deleteArticleDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/article/{id}";
		HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
		restTemplate.exchange(url,HttpMethod.DELETE,requestEntity, void.class,4);
	}
	
	public static void main(String args[]) {
		RestClientUtil util = new RestClientUtil();
		util.getAllArticlesDemo();
		util.getArticleByDemo();
	}

}
