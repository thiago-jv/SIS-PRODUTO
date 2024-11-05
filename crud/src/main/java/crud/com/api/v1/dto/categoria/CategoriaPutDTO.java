package crud.com.api.v1.dto.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaPutDTO {

    @ApiModelProperty(value = "Id", example = "1", required = true)
    private Long id;

    @ApiModelProperty(value = "Descrição da categoria", example = "leve", required = true)
    private String descricao;

}
