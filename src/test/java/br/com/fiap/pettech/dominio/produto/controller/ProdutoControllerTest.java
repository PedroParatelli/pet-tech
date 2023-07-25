package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//carrega todo contexto do web mvc
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean//mockando os comportamentos da service
    ProdutoService produtoService;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOCasoIdExista() throws Exception{
        UUID id = UUID.fromString("bfa3d7b8-b58a-4306-bae5-81c4ae0eb55");

        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("PC");
        produto.setPreco(300.56);
        produto.setDescricao("Pc Gamer");
        produto.setUrlImage("url 1");
        produto.setId(id);

        Mockito.when(produtoService.findById(id)).thenReturn(produto);

        ResultActions result = mockMvc.perform(get("/produtos/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
