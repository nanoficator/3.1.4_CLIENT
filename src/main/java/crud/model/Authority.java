package crud.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Authority objRole = (Authority) obj;
        if (this.id.equals(objRole.id) && this.name.equals(objRole.name)) {
            return true;
        }
        return false;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
