package crud.com.api.v1.dto.produto;

import crud.com.api.v1.dto.categoria.CategoriaId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPostDTO {

    private String descricao;
    private CategoriaId categoria;
}
