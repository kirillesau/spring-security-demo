package de.kirill.demoapi;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.StringJoiner;

@RestController
@RequestMapping(path = "callme/demo")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

  @PreAuthorize(value = "hasAuthority('SCOPE_TEST')")
  @GetMapping
  public String getActualTime() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    StringJoiner sj = new StringJoiner("\n\r");
    String result = sj.add("Scopes: " + authentication.getAuthorities())
        .add("Details: " + authentication.getDetails())
        .add("getIssuedAt: " + ((Jwt) authentication.getPrincipal()).getIssuedAt())
        .add("getExpiresAt: " + ((Jwt) authentication.getPrincipal()).getExpiresAt())
        .add("Aktuelle Zeit: " + new Date())
        .toString();
    log.info(result);
    return result;
  }

}
