package crud.com.api.v1.dto.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaPostDTO {

    @ApiModelProperty(value = "Descrição da categoria", example = "leve", required = true)
    private String descricao;

}
