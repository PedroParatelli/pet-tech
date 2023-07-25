package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {

    @InjectMocks//mockando o serviço, não injetando diretamente.
    private ProdutoService service;

    @Mock//mockando o comportamento da classe.
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;
    private ProdutoDTO produtoDTO;
    private Produto produto;
    private String nomeAtualizado;

    @BeforeEach
    public void setUp(){
        idExistente = UUID.fromString("bfa3d7b8-b58a-4306-bae5-81c4ae0eb55");
        idNaoExistente = UUID.fromString("bfa3d7b8-b58a-4306-bae5-81c4ae0eb55");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDTO();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "produto atualizado";

        //mokando o comportamento da repository (findById - no caso essa retorna um Produto produto/findAll - retornando um Pagerequest)
        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO(){
        Page produtoDTO = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId(){
        ProdutoDTO produtoDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmaExcecaoCasoIdNaoExista(){
        Assertions.assertThrows(ClassCastException.class, () -> {
            service.findById(this.idNaoExistente);
        });
    }


}
