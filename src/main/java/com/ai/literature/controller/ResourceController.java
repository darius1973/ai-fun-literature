package com.ai.literature.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceController {
    @GetMapping("/VAADIN/**")
    public ResponseEntity<Resource> serveStaticResource(HttpServletRequest request) {
        String path = request.getRequestURI().replaceFirst("/VAADIN/", "");
        Resource resource = new ClassPathResource("META-INF/VAADIN/webapp/" + path);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resource);
    }
}
