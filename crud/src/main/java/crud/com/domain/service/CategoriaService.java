package crud.com.domain.service;

import crud.com.domain.model.Categoria;
import crud.com.domain.repository.CategoriaRepository;
import crud.com.infra.exception.CategoriaNaoEncontadoException;
import crud.com.infra.exception.EntidadeEmUsoException;
import crud.com.infra.utils.Messages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoriaNaoEncontadoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(Messages.MSG_CATEGORIA_EM_USO, id));
        }
    }

    public Categoria atualizar(Long id, Categoria categoria){
        var categoriaSalvar = categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(categoria, categoriaSalvar, "id");
        return categoriaRepository.save(categoriaSalvar);

    }

    public Categoria buscarOuFalhar(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow(() -> new CategoriaNaoEncontadoException(idCategoria));
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

}
