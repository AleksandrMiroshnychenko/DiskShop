package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Genre;

public class GenreDAO extends DAOFactory implements Queries {

	static PreparedStatement pstat;
	Statement stat;
	ResultSet res;
	//static Connection con;

	public GenreDAO() {
	}

	public ArrayList<Genre> getGenreNames() {

		ArrayList<Genre> Genres = new ArrayList<Genre>();
		try {
			Connection con = super.getDBConnection();

			stat = con.createStatement();
			res = stat.executeQuery("select id, name from genre;");

			while (res.next()) {
				Genre genre = new Genre();
				genre.setId(res.getInt(1));
				genre.setName(res.getString(2));
				Genres.add(genre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return Genres;

	}
	

	public void addGenreBatch(Genre entity, Connection con, int currentDiskId, List<Integer>exGenre) {
		try {
			List<String>name = entity.getGenreBatch();
				

			pstat = con.prepareStatement(relate_disk_genre);

			for (Integer in : exGenre) {
				pstat.setInt(1, currentDiskId);
				System.out.println("String Genre" + in);
				pstat.setInt(2, in);
				pstat.addBatch();
			}
			pstat.executeBatch();
			
			CallableStatement cstat = con.prepareCall("{call exGenre(?, ?)}");
			for (String string : name) {
				cstat.setInt(1, currentDiskId);
				System.out.println("GenreName" + string);
				cstat.setString(2, string);
				cstat.addBatch();
			}
				cstat.executeBatch();
				
			}
				catch (Exception e) {}
	}

	static Genre getGenreByDiskId(int id, Connection con){
		
		Genre genre = new Genre();
		ArrayList<String> genreNameBatch = new ArrayList<String>();
		ArrayList<Integer> genreIdBatch = new ArrayList<Integer>();

		try {
			pstat = con.prepareStatement(getGenreRelatedWithDisk);
			pstat.setInt(1, id);
			ResultSet res = pstat.executeQuery();

			while (res.next()) {
				genreIdBatch.add(res.getInt(1));
				genreNameBatch.add(res.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		genre.setGenreIdBatch(genreIdBatch);
		genre.setGenreBatch(genreNameBatch);
		
		return genre;
		
	}

}
