package crud.com.infra.exception.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Campo {

    private String campo;
    private String mensagemUsuario;


}
