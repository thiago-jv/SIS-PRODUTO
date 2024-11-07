package crud.com.api.v1.dto.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaId {

    @ApiModelProperty(value = "Id", example = "1", required = true)
    private Long id;
}
