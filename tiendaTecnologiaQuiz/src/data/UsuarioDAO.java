package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO implements CRUD_Operation<Usuario, String>{
	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void save(Usuario usuario) {
		String sql = "INSERT INTO Usuario (nickname, contraseña) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, usuario.getNickname());
			stmt.setString(2, usuario.getContraseña());
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException("Error al guardar Usuario: " + e.getMessage(), e);
		}
	}

	@Override
	public void update(Usuario usuario) {
		String sql = "UPDATE Usuario SET password = ? WHERE nickname = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getNickname());
			stmt.setString(2, usuario.getContraseña());
		} catch (SQLException e) {
			throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(String referencia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean authenticate(String referencia) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean authenticate1(String nickname) {
		return existNICK(nickname);
	}
	
	public boolean existNICK(String nickname) {
		String sql = "SELECT COUNT(*) FROM Usuario WHERE nickname = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, nickname);
			ResultSet rs = stmt.executeQuery();
			return rs.next() && rs.getInt(1) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error al verificar ID: " + e.getMessage(), e);
		}
	}

	@Override
	public ArrayList<Usuario> fetch() {
		ArrayList<Usuario> Users = new ArrayList<>();
		String sql = "SELECT nickname, contraseña FROM Usuario";
		try (Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()) {
				Users.add(new Usuario(rs.getString("nickname"), rs.getString("password")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al cargar usuarios: " + e.getMessage(), e);
		}
		return Users;
	}

}