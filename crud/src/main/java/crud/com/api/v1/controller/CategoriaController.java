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

@RestController
@RequestMapping(value = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @PostMapping
    public CategoriaResponseDTO criar(@Valid @RequestBody CategoriaPostDTO categoriaPostDTO, HttpServletResponse response) {
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.criar(categoriaMapper.toCategoria(categoriaPostDTO)));
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaPutDTO categoriaPutDTO) {
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.atualizar(id, categoriaMapper.toCategoria(categoriaPutDTO)));
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return categoriaMapper.toListCategoriaResponseDTO(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaMapper.toCategoriaResponseDTO(categoriaService.buscarOuFalhar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
    }


}
