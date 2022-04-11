package de.kirill.demoapi;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "demo")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

  @PreAuthorize("hasAuthority('SCOPE_TEST')")
  @GetMapping
  public String getActualTime() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    log.info("Scopes: " + authentication.getAuthorities());
    log.info("Aktuelle Zeit wird ausgegeben");
    return new Date().toString();
  }

}
