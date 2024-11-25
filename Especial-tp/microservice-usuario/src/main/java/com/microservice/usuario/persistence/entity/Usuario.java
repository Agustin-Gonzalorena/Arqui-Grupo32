package com.microservice.usuario.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private int telefono;
    @Column(name = "fecha_de_alta")
    private LocalDate fechaDeAlta;
    private Boolean ban; //BAN o n
    @ManyToMany(mappedBy = "usuarios")
    private List<Cuenta> cuentas;
    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") }
    )
    private Set<Authority> authorities = new HashSet<>();

    public Usuario(final String email){
        this.email = email.toLowerCase();
    }
    public void setAuthorities(final Collection<Authority> authorities) {
        this.authorities = new HashSet<>(authorities);
    }
}
