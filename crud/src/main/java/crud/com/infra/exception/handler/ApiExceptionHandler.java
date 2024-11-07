package crud.com.infra.exception.handler;

import crud.com.infra.exception.EntidadeEmUsoException;
import crud.com.infra.exception.EntidadeNaoEncontradaException;
import crud.com.infra.exception.NegocioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";


	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoProblema tipoProblema  = TipoProblema.ENTIDADE_NAO_ENCONTRADA;

		Problema problema = createProblemaBuilder(status, tipoProblema, ex.getMessage())
				.mensagemUsuario(ex.getMessage())
				.build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(),
				status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(
			EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		TipoProblema tipoProblema = TipoProblema.ENTIDADE_EM_USO;

		Problema problema = createProblemaBuilder(status, tipoProblema, ex.getMessage())
				.mensagemUsuario(ex.getMessage())
				.build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(),
				status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		TipoProblema tipoProblema = TipoProblema.ERRO_NEGOCIO;

		Problema problema = createProblemaBuilder(status, tipoProblema, ex.getMessage())
				.mensagemUsuario(ex.getMessage())
				.build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(),
				status, request);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        TipoProblema tipoProblema = TipoProblema.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        log.error(ex.getMessage(), ex);

        Problema problema = createProblemaBuilder(status, tipoProblema, detail)
                .mensagemUsuario(detail)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		TipoProblema tipoProblema = TipoProblema.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		List<Campo> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			Campo campo = new Campo(violation.getPropertyPath().toString(), violation.getMessage());
			errors.add(campo);
		}

		Problema problema = createProblemaBuilder(status, tipoProblema, detail)
				.mensagemUsuario(detail)
				.campos(errors)
				.build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		TipoProblema tipoProblema = TipoProblema.DADOS_INVALIDOS;
	 	String detail = "Houve uma violação de integridade de dados, favor verificar duplicidade de dados";
     	Problema problema = createProblemaBuilder(status, tipoProblema, detail)
				.mensagemUsuario(detail)
				.build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	private Problema.ProblemaBuilder createProblemaBuilder(HttpStatus status, TipoProblema tipoProblema, String detail) {
		return Problema.builder().status(status.value())
				.dataHora(OffsetDateTime.now())
				.status(status.value())
				.type(tipoProblema.getUri())
				.title(tipoProblema.getTitulo())
				.detail(detail);
	}

}
