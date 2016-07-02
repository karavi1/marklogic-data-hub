package com.marklogic.quickstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    protected final static Logger logger = LoggerFactory.getLogger(AppController.class);

    /**
     * Assumes that the root URL should use a template named "index", which presumably will setup the Angular app.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index.html";
    }

    @RequestMapping(value = "/install", method = RequestMethod.GET)
    public String install() {
        return "index.html";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return "index.html";
    }
}
