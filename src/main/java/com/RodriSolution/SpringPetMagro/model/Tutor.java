package com.RodriSolution.SpringPetMagro.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String celular;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.PERSIST)
    private List<Pet> pets;


    public Tutor() {}

    public Tutor(Long id, String nome, String email, String celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
