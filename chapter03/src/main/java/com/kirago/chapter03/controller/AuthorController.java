package com.kirago.chapter03.controller;

import com.kirago.chapter03.model.Author;
import com.kirago.chapter03.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AuthorController
 * @Description TODO
 * @Author kirago
 * @Date 2018/11/13 18:13
 * @Version 1.0
 **/
@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAuthorList(HttpServletRequest request) {
		List<Author> authorList = this.authorService.findAll();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("total", authorList.size());
		param.put("rows", authorList);
		return param;
	}

	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
	public Author getAuthor(@PathVariable Long userId, HttpServletRequest request) {
		Author author = this.authorService.findAuthor(userId);
		if (author == null) {
			throw new RuntimeException("查询错误");
		}
		return author;
	}
}
