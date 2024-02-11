package org.good.job.currency.project.controller;

import lombok.RequiredArgsConstructor;
import org.good.job.currency.project.service.ExternalApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor

@RestController
@RequestMapping("/apis")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    @GetMapping
    public ResponseEntity<List<String>> getAvailableExternalApis() {
        List<String> availableExternalApis = externalApiService.getAvailableExternalApis();
        return ResponseEntity.ok(availableExternalApis);
    }

}
