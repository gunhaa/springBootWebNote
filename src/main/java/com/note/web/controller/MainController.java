package com.note.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import static com.note.web.controller.HttpRequestLogger.ReqLogger;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        log.info("GET : /");
        ReqLogger(request);

        return "home";
    }

}
