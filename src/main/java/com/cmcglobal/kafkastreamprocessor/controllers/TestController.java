package com.cmcglobal.kafkastreamprocessor.controllers;

import io.sentry.Sentry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/public")
public class TestController {

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return ResponseEntity.ok(true);
    }
}
