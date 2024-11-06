package crud.com.infra.exception;

public class ProdutoNaoEncontadoException extends EntidadeNaoEncontradaException {

	public ProdutoNaoEncontadoException(String messagem) {
		super(messagem);
	}

	public ProdutoNaoEncontadoException(Long idProduto) {
		this(String.format("Não existe cadastro de produto com o código %d", idProduto));
	}

}
