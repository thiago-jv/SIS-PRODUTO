package crud.com.api.v1.controller;

import crud.com.api.v1.dto.produto.ProdutoPostDTO;
import crud.com.api.v1.dto.produto.ProdutoPutDTO;
import crud.com.api.v1.dto.produto.ProdutoResponseDTO;
import crud.com.api.v1.mapper.ProdutoMapper;
import crud.com.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    private static Logger log = Logger.getLogger(ProdutoController.class.getName());

    @PostMapping
    public ProdutoResponseDTO criar(@Valid @RequestBody ProdutoPostDTO produtoPostDTO, HttpServletResponse response) {
        log.info("Cadastrando Produto");
        return produtoMapper.toProdutoResponseDTO(produtoService.criar(produtoMapper.toProduto(produtoPostDTO)));
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoPutDTO produtoPutDTO) {
        log.info("Atualizando Produto");
        return produtoMapper.toProdutoResponseDTO(produtoService.atualizar(id, produtoMapper.toProduto(produtoPutDTO)));
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        log.info("Listando Produto");
        return produtoMapper.toListProdutoResponseDTO(produtoService.listar());
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        log.info("Busca Produto por ID");
        return produtoMapper.toProdutoResponseDTO(produtoService.buscarOuFalhar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("Deleta Produto por ID");
        produtoService.deletar(id);
    }

}
