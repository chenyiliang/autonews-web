package com.github.cyl.autonews.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.cyl.autonews.service.AutoNewsService;

@Controller
@RequestMapping("/autonews")
public class AutoNewsController {
	@Autowired
	private AutoNewsService autoNewsService;

	@RequestMapping("/generate/{date}")
	@ResponseBody
	public Map<String, String> generateOneArticle(@PathVariable String date) {
		Map<String, String> map = new HashMap<String, String>();
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4, 6));
		String articleStr = autoNewsService.generateOneArticleStr(year, month);
		map.put("article", articleStr);
		System.out.println(map);
		return map;
	}
}
