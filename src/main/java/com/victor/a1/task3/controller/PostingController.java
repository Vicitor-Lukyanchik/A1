package com.victor.a1.task3.controller;

import com.victor.a1.task3.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/postings")
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("isAuthorized") Optional<Boolean> isAuthorized,
                        @RequestParam("startDate") Optional<String> startDate,
                        @RequestParam("endDate") Optional<String> endDate) {

        String message = "";
        try {
            model.addAttribute("postings", postingService
                    .findAuthorizedCsvPostingsByDates(startDate.get(), endDate.get(), !isAuthorized.isEmpty()));
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        model.addAttribute("message", message);
        return "index";
    }
}
