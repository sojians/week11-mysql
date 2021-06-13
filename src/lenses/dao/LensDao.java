package lenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import lenses.entity.Lens;

public class LensDao {

	public List<Lens> findLenses() {
		try(Connection connection = DbConnection.getConnection()) {
			String sql = "SELECT * FROM lenses ORDER BY lens_id";
			
			try(PreparedStatement statement = connection.prepareStatement(sql)) {
				try(ResultSet rs = statement.executeQuery()) {
					List<Lens> lenses = new LinkedList<>();
					
					while(rs.next()) {
						int lensId = rs.getInt("lens_id");
						String nickname = rs.getString("nickname");
						Lens lens = new Lens(lensId, nickname);
						lenses.add(lens);
					}
					
					return lenses;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void createLens(String nickname) {
		String sql = "INSERT INTO lenses (nickname) VALUES (?)";
		try(Connection connection = DbConnection.getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, nickname);
				statement.executeUpdate();
			}
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}
		
	}

	public void updateLensNickname(int lensId, String nickname) {
		try(Connection connection = DbConnection.getConnection()) {
			String sql = "UPDATE lenses SET nickname = ? WHERE lens_id = ?";
			
			try(PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, nickname);
				statement.setInt(2, lensId);
				statement.executeUpdate();
			}
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}
		
	}

	public void deleteLens(int lensId) {
		try(Connection connection = DbConnection.getConnection()) {
			String sql = "DELETE FROM lenses WHERE lens_id = ?";
			
			try(PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, lensId);
				statement.executeUpdate();
			}
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}		
	}

}
