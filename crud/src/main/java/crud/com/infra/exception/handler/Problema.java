package crud.com.infra.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String mensagemUsuario;
	private OffsetDateTime dataHora;
	private List<Campo> campos;

}
