package crud.com.api.v1.controller;

import crud.com.api.v1.dto.categoria.CategoriaPostDTO;
import crud.com.api.v1.dto.categoria.CategoriaPutDTO;
import crud.com.api.v1.dto.categoria.CategoriaResponseDTO;
import crud.com.api.v1.mapper.CategoriaMapper;
import crud.com.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    private static Logger log = Logger.getLogger(CategoriaController.class.getName());

    @PostMapping
    public CategoriaResponseDTO criar(@Valid @RequestBody CategoriaPostDTO categoriaPostDTO, HttpServletResponse response) {
        log.info("Cadastrando Categoria");
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.criar(categoriaMapper.toCategoria(categoriaPostDTO)));
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaPutDTO categoriaPutDTO) {
        log.info("Atualizando Categoria");
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.atualizar(id, categoriaMapper.toCategoria(categoriaPutDTO)));
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        log.info("Listando Categoria");
        return categoriaMapper.toListCategoriaResponseDTO(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        log.info("Busca Categoria por ID");
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.buscarOuFalhar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("Deleta Categoria por ID");
        categoriaService.deletar(id);
    }


}
