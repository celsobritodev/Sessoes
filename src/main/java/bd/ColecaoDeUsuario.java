package bd;

public interface ColecaoDeUsuario extends Colecao<Usuario> {

	public Usuario porNome( String nome ) throws ColecaoException;

}
