package crud.com.domain.service;

import crud.com.domain.model.Produto;
import crud.com.domain.repository.ProdutoRepository;
import crud.com.infra.exception.EntidadeEmUsoException;
import crud.com.infra.exception.ProdutoNaoEncontadoException;
import crud.com.infra.utils.Messages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontadoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(Messages.MSG_CATEGORIA_EM_USO, id));
        }
    }

    public Produto atualizar(Long id, Produto categoria){
        var produtoSalvar = produtoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(categoria, produtoSalvar, "id");
        return produtoRepository.save(produtoSalvar);

    }

    public Produto buscarOuFalhar(Long idProduto) {
        return produtoRepository.findById(idProduto).orElseThrow(() -> new ProdutoNaoEncontadoException(idProduto));
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }


}
