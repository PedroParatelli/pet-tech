package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class ProdutoDTO {

    private UUID id;
    @NotBlank(message = "nome obrigatorio")
    private String nome;
    private String descricao;
    private String urlImage;
    @Positive(message = "o valor do produto tem que ser positivo")
    private double preco;

    public ProdutoDTO(){}

    public ProdutoDTO(UUID id, String nome, String descricao, String urlImage, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.preco = preco;
    }

    public ProdutoDTO (Produto entidade){
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.descricao = entidade.getDescricao();
        this.urlImage = entidade.getUrlImage();
        this.preco = entidade.getPreco();
    }

    public UUID getId() {
        return id;
    }

    public ProdutoDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ProdutoDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public ProdutoDTO setUrlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public ProdutoDTO setPreco(double preco) {
        this.preco = preco;
        return this;
    }
}
