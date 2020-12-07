package com.practicum.pu.georgidinov.shoppinglist.util.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DecoupledLogicSetup {

    //== fields ==
    private final SpringResourceTemplateResolver templateResolver;

    //== constructors ==
    @Autowired
    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }//end of constructor

    //== init ==
    @PostConstruct
    public void init() {
        this.templateResolver.setUseDecoupledLogic(true);
        log.info("Decoupled template logic enabled");
    }//end of method init

}