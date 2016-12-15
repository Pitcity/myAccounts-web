package com.itovpinets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Controller
public class HomeController {
    @RequestMapping (value = "/")
    public String home() {
        return "hello:)";
    }

}
