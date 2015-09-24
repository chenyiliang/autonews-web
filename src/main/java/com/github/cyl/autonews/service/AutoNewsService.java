package com.github.cyl.autonews.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.cyl.autonews.pojo.analysis.Article;
import com.github.cyl.autonews.pojo.analysis.Paragraph;
import com.github.cyl.autonews.pojo.analysis.Section;
import com.github.cyl.autonews.pojo.analysis.Sentence;
import com.yicai.autonews.articlegenerator.ArticleGenerator;

@Service
public class AutoNewsService {
	public String generateOneArticleStr(int year, int month) {
		String articleStr = "";
		Article article = new ArticleGenerator().newsArticle(year, month);
		String title = article.getTitle();
		articleStr = assembleArticleStr(articleStr, title, true, "<center><h4>", "</h4></center>");
		List<Section> sections = article.getSections();
		for (Section section : sections) {
			String subTitle = section.getSubTitle();
			articleStr = assembleArticleStr(articleStr, subTitle, true, "<h5>", "</h5>");
			List<Paragraph> paragraphs = section.getParagraphs();
			for (Paragraph paragraph : paragraphs) {
				List<Sentence> sentences = paragraph.getSentences();
				for (int i = 0; i < sentences.size(); i++) {
					Sentence sentence = sentences.get(i);
					String str = sentence.getSentence();
					str = wrapSentence(str, sentence.getType(), sentence.getSimilarity());
					if (i == 0) {
						articleStr = assembleArticleStr(articleStr, str, false,
								"<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", "");
					} else if (i == sentences.size() - 1) {
						articleStr = assembleArticleStr(articleStr, str, false, "", "</div>");
					} else {
						articleStr = assembleArticleStr(articleStr, str, false, "", "");
					}
				}
				articleStr = assembleArticleStr(articleStr, "\r\n", false, "", "");
			}
		}
		return articleStr;
	}

	private String assembleArticleStr(String origin, String append, boolean isWrap, String prefix, String suffix) {
		if (append != null && !append.isEmpty()) {
			origin += (prefix + append + suffix);
			if (isWrap) {
				origin += "\r\n";
			}
		}
		return origin;
	}

	private String wrapSentence(String sentence, int type, double similarity) {
		if (type == 1) {
			sentence = "<span style='color: darkgreen;'>" + sentence + "</span>";
		} else if (type == 2) {
			sentence = "<span style='color: blue;'>" + sentence + "</span><span style='color: red;'>[" + similarity
					+ "]</span>";
		}
		return sentence;
	}

	public static void main(String[] args) {
		String articleStr = new AutoNewsService().generateOneArticleStr(2015, 6);
		System.out.println(System.getenv("Path"));
	}
}
