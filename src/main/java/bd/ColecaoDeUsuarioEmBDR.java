package bd;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColecaoDeUsuarioEmBDR implements ColecaoDeUsuario {

	private Connection conexao;

	public ColecaoDeUsuarioEmBDR(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Usuario> todos() throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> la = null;
		try {
			la = new ArrayList<Usuario>();
			String sql = "SELECT id, nome, senha FROM usuario";
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario a = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
				la.add(a);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os usuarios do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return la;
	}
	
	
	@Override
	public Usuario porNomeExato(String nome) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario a = null;
		try {
			String sql = "SELECT id, nome, senha FROM usuario WHERE nome=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			if (rs.next()) {
				a = new Usuario(rs.getInt("id"), rs.getString("nome"),rs.getString("senha"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o usuario do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return a;
	}
	
	
	
	

	@Override
	public Usuario porId(int id) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario a = null;
		try {
			String sql = "SELECT id, nome, senha FROM usuario WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				a = new Usuario(rs.getInt("id"), rs.getString("nome"),rs.getString("senha"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o usuario do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return a;
	}

	
	
	@Override
	public List<Usuario> porNome(String nome) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> la = null;
		try {
			la = new ArrayList<Usuario>();
			String sql = "SELECT id, nome, senha FROM usuario WHERE nome LIKE ?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario a = new Usuario(rs.getInt("id"), rs.getString("nome"),rs.getString("senha"));
				la.add(a);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os usuarios do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return la;
	}


	@Override
	public void inserir(Usuario usuario) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO usuario (nome,senha) VALUES (?,?)";
			ps = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				usuario.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao inserir o usuario no banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	@Override
	public void alterar(Usuario usuario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE usuario SET nome=?,senha=? WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao alterar o usuario no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	@Override
	public void remover(Usuario usuario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM usuario WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, usuario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao remover o usuario no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	

}
