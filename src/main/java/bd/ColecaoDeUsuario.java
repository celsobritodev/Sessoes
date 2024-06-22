package bd;

import java.util.List;

public interface ColecaoDeUsuario extends Colecao<Usuario> {

	public List<Usuario> porNome(String nome) throws ColecaoException;
	public Usuario porNomeExato(String nome) throws ColecaoException;


}


