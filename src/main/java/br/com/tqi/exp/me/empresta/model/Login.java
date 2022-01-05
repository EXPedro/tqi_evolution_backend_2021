package br.com.tqi.exp.me.empresta.model;

import br.com.tqi.exp.me.empresta.security.role.LoginRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/*
classe da entidade Login
implementa UserDetails
requisito do Spring Security
*/

@Entity
@Table(name="login")
@Getter
@Setter
@NoArgsConstructor
public class Login implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private LoginRole loginRole;
    private Boolean locked;
    private Boolean enabled;

    public Login(String email, String password, LoginRole loginRole) {
        this.email = email;
        this.password = password;
        this.loginRole = loginRole;
        this.enabled = true;
        this.locked = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(loginRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Login login = (Login) o;
        return id != null && Objects.equals(id, login.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
