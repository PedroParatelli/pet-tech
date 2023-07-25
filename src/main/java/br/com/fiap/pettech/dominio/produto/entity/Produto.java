package br.com.fiap.pettech.dominio.produto.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImage;
    private double preco;

    public Produto() {}

    public Produto(String nome, String descricao, String urlImage, double preco) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public Produto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Produto setUrlImage(String urlImagem) {
        this.urlImage = urlImagem;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public Produto setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;

        return getId().equals(produto.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImagem='" + urlImage + '\'' +
                ", preco=" + preco +
                '}';
    }


}
