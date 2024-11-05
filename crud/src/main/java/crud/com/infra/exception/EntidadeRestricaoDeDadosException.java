package crud.com.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EntidadeRestricaoDeDadosException extends RuntimeException {

	public EntidadeRestricaoDeDadosException(String mensagem) {
		super(mensagem);
	}

}
