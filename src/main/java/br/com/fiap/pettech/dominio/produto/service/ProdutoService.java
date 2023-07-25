package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repo;

    public Page<ProdutoDTO> findAll(PageRequest pagina){
        var produtos = repo.findAll(pagina);
        return produtos.map(produto -> new ProdutoDTO());
    }

    public ProdutoDTO findById(UUID id){
        var produto = repo.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO produto) {
        Produto entity = new Produto();
        entity.setNome(produto.getNome());
        entity.setDescricao(produto.getDescricao());
        entity.setUrlImage(produto.getUrlImage());
        entity.setPreco(produto.getPreco());

        var produtoSaved = repo.save(entity);
        return new ProdutoDTO(produtoSaved);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produto){
        try{
            Produto buscaProduto = repo.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImage(produto.getUrlImage());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repo.save(buscaProduto);

            return new ProdutoDTO(buscaProduto);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto nao encontrado, id: " + id);
        }
    }

    public void delete(UUID id){
        try{
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }

}
