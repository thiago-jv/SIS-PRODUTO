package crud.com.api.v1.mapper;


import crud.com.api.v1.dto.produto.ProdutoPostDTO;
import crud.com.api.v1.dto.produto.ProdutoPutDTO;
import crud.com.api.v1.dto.produto.ProdutoResponseDTO;
import crud.com.domain.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toProduto(ProdutoPostDTO produtoPostDTO);

    Produto toProduto(ProdutoPutDTO produtoPutDTO);

    ProdutoResponseDTO toProdutoResponseDTO(Produto produto);

    List<ProdutoResponseDTO> toListProdutoResponseDTO(List<Produto> produtos);

}
