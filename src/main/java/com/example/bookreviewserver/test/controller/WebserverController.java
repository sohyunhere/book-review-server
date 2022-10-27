package com.example.bookreviewserver.test.controller;
import com.example.bookreviewserver.model.Category;
import com.example.bookreviewserver.service.BoardService;
import com.example.bookreviewserver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebserverController {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CategoryService categoryService;
    private final BoardService boardService;
    @GetMapping("/")
    public List<Category> main() {
        System.out.println("dmddmdm");
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping("/webclient/{param}")
    public String testWebClient(
            @PathVariable String param,
            @RequestHeader HttpHeaders headers,
            @CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {

        log.info(">>>> Cookie 'httpclient-type={}'", httpClientType);

        headers.forEach((key, value) -> {
            log.info(String.format(">>>>> Header '%s' => %s", key, value));
        });

        log.info("### Received: /webclient/" + param);

        String msg = param + " => Working successfully !!!";
        log.info("### Sent: " + msg);
        return msg;
    }
}