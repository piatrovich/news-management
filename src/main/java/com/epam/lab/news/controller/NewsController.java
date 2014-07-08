package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.data.service.NewsService;
import com.epam.lab.news.exception.bean.ArticleNotFoundException;
import com.epam.lab.news.logic.validation.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Describes main RESTful application controller
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Controller
public class NewsController {
    /** Service for working with data */
    @Autowired
    private NewsService service;

    /** Validator for checking beans before saving */
    @Autowired
    private ArticleValidator validator;

    /**
     * Handles requests for view main page
     *
     * @param model Model object
     * @return Name of 'view'
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("articles", service.getAll());
		return "index";
	}

    /**
     * Handles requests for view full single article
     *
     * @param id Requested article id
     * @param model Model object
     * @return Name of 'view'
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable Long id, Model model) {
        Article article = service.get(id);
        if (article == null) {
            throw new ArticleNotFoundException();
        }
        model.addAttribute("article", article);
        return "view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addArticle(Model model) {
        model.addAttribute("proposedArticle", new Article());
        return "add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editArticle(@PathVariable Long id, Model model) {
        Article article = service.get(id);
        if (article == null) {
            throw new ArticleNotFoundException();
        }
        model.addAttribute("article", article);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String saveEdited(@PathVariable Long id, Article article, BindingResult result) {
        validator.validate(article, result);
        if (result.hasErrors()) {
            return "redirect:/edit/"+id;
        }
        service.saveChanges(article);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable Long id, Model model) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveArticle(Article article, BindingResult result) {
        validator.validate(article, result);
        if (result.hasErrors()) {
            return "add";
        }
        service.save(article);
        return "redirect:/";
    }

}