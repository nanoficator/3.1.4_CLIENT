package crud.service;

import crud.config.ServerConfig;
import crud.model.Authority;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class AuthorityServiceREST implements AuthorityService {

    private RestTemplate restTemplate;
    ServerConfig serverConfig;

    @Autowired
    public AuthorityServiceREST(RestTemplateBuilder restTemplateBuilder,
                                ServerConfig serverConfig) {
        this.restTemplate = restTemplateBuilder.basicAuthentication("admin", "admin").build();
        this.serverConfig = serverConfig;
    }

    @Override
    public Collection<Authority> getAllAuthorities() {
        ResponseEntity<Collection<Authority>> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/all-authorities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Authority>>() {}
        );
        return responseEntity.getBody();
    }

    @Override
    public Authority getAuthorityByName(String name) {
        ResponseEntity<Authority> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/authority-by-name",
                HttpMethod.POST,
                new HttpEntity<>(name, new HttpHeaders()),
                Authority.class
        );
        return responseEntity.getBody();
    }

}
