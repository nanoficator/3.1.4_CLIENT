package crud.service;

import crud.model.Authority;

import java.util.Collection;

public interface AuthorityService {

    Collection<Authority> getAllAuthorities();

    Authority getAuthorityByName(String name);

}
