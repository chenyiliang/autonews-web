package com.github.cyl.autonews.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.cyl.autonews.pojo.analysis.Article;
import com.github.cyl.autonews.pojo.analysis.Paragraph;
import com.github.cyl.autonews.pojo.analysis.Section;
import com.github.cyl.autonews.pojo.analysis.Sentence;

@Service
public class AutoNewsService {
	public String generateOneArticleStr(int year, int month) {
		String articleStr = "";
		Article article = new Article();
		String title = article.getTitle();
		assembleArticleStr(articleStr, title, true);
		List<Section> sections = article.getSections();
		for (Section section : sections) {
			String subTitle = section.getSubTitle();
			assembleArticleStr(articleStr, subTitle, true);
			List<Paragraph> paragraphs = section.getParagraphs();
			for (Paragraph paragraph : paragraphs) {
				List<Sentence> sentences = paragraph.getSentences();
				for (Sentence sentence : sentences) {
					String str = sentence.getSentence();
					assembleArticleStr(articleStr, str, false);
				}
				assembleArticleStr(articleStr, "\r\n", false);
			}
		}
		return "样本文章" + year + "年" + month + "月，时间戳" + System.currentTimeMillis()
				+ "***************************************************************1234567890";
	}

	private void assembleArticleStr(String origin, String append, boolean isWrap) {
		if (append != null && !append.isEmpty()) {
			origin += append;
			if (isWrap) {
				origin += "\r\n";
			}
		}
	}
}
