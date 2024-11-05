package crud.com.infra.exception;

public class CategoriaNaoEncontadoException extends EntidadeNaoEncontradaException {

	public CategoriaNaoEncontadoException(String messagem) {
		super(messagem);
	}

	public CategoriaNaoEncontadoException(Long idCategoria) {
		this(String.format("Não existe cadastro de categoria com o código %d", idCategoria));
	}

}
