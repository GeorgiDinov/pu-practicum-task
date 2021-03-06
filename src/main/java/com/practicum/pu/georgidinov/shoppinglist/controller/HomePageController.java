package com.practicum.pu.georgidinov.shoppinglist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_DEFAULT;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_HOME;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_HOME_HTML;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_INDEX;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_INDEX_HTML;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.HOME_PAGE_MAPPING_SLASH;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.LOGIN_PAGE_MAPPING_LOGIN;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VIEW_NAME_HOME_PAGE;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VIEW_NAME_LOGIN_PAGE;

@Slf4j
@Controller
public class HomePageController {

    @GetMapping(LOGIN_PAGE_MAPPING_LOGIN)
    public String login() {
        log.info("login page loaded...");
        return VIEW_NAME_LOGIN_PAGE;
    }

    @GetMapping({HOME_PAGE_MAPPING_DEFAULT, HOME_PAGE_MAPPING_SLASH,
            HOME_PAGE_MAPPING_HOME, HOME_PAGE_MAPPING_HOME_HTML,
            HOME_PAGE_MAPPING_INDEX, HOME_PAGE_MAPPING_INDEX_HTML})
    public String home() {
        log.info("home page loaded...");
        return VIEW_NAME_HOME_PAGE;
    }

}