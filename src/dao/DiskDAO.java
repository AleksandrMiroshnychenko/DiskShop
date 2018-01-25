package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entity.Actor;
import entity.Disk;
import entity.Genre;
import entity.User;

public class DiskDAO extends DAOFactory implements Queries {

	Connection con;
	PreparedStatement state;
	Statement stat;
	ResultSet resSet;

	public DiskDAO() {

	}

	public byte addDisk(Disk entity, List<Integer> exActor, List<Integer> exGenre) throws SQLException {
			
		Connection con =super.getDBConnection();
		
		try {
			ResultSet resSet;

			PreparedStatement state = con.prepareStatement("select max(id) from disk;");

			int currentDiskId = 1;

			try {
				resSet = state.executeQuery();
				if (resSet.next()) {
					currentDiskId = resSet.getInt(1);
					currentDiskId++;
				}
			} catch (NullPointerException | SQLException e) {
				e.printStackTrace();
				currentDiskId = 1;
			}
			System.out.println("new Id " + currentDiskId);
			entity.setId(currentDiskId);
			state = con.prepareStatement(addDisk);

			diskSet(state, entity);
			System.out.println("updated");

			new GenreDAO().addGenreBatch(entity.getGenre(), con, currentDiskId, exGenre);

			Actor actors = entity.getActors();

			new ActorDAO().addActorBatch(actors, con, currentDiskId, exActor);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		byte error;

		Actor actors = entity.getActors();
		error = CheckUnique(entity, actors);
		if (error != 0) {
			con.rollback();
			return error;
		}

		
		con.commit();
		System.out.println("Commit");

		state.close();

		return 0;
	}
	
	public void deleteDisk(int id) throws Exception {
		try {
			con = super.getDBConnection();

		
		PreparedStatement state = con.prepareStatement("DELETE FROM `disk` WHERE `disk`.`id`= ?;");
		state.setInt(1, id);
		state.executeUpdate();

		con.commit();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	private void diskSet(PreparedStatement state, Disk entity) throws SQLException {
		state.setString(1, entity.getTitle());
		state.setByte(2, entity.getSellable());
		state.setInt(3, entity.getReleaseDate());
		state.setFloat(4, entity.getPrice());
		state.setInt(5, entity.getStored());
		state.setString(6, entity.getDescription());
		state.setInt(9, entity.getId());
		state.setString(7, entity.getCover());
		state.setInt(8, entity.getRating().ordinal()+1);
		
		state.executeUpdate();
	}

	public void updateDisk(Disk disk) {
		try {
			Connection con = super.getDBConnection();
			state = con.prepareStatement(updateDisk);
			diskSet(state, disk);
			//state.executeUpdate();
			con.commit();
			System.out.println("Commit");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte CheckUnique(Disk entity, Actor actor) {
		try {

			int length = 0;
			List<String> actorNames = new ArrayList<String>();
			try {
				actorNames = actor.getActorNameBatch();
				length = actorNames.size();
			} catch (NullPointerException e) {
			}

			int counter = 0;

			con = super.getDBConnection();
			state = con.prepareStatement(checkUniqueDisk);

			for (int i = 0; i < length; i++) {
				state.setString(1, entity.getTitle());
				state.setString(2, actorNames.get(i));
				state.setInt(3, entity.getReleaseDate());
				ResultSet res = state.executeQuery();

				if (res.next()) {
					System.out.println(counter);
					System.out.println(res.getString(1));
					counter++;
				}
			}
			if (counter == length&&length!=0) {
				return -1;
			}

		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return 0;
	}

	public int getDiskCount(String search) {
		try {
			con = super.getDBConnection();
			stat = con.createStatement();
			ResultSet resSet;
			if (search == null || search.isEmpty()) {
				resSet = stat.executeQuery("select count(`id`) from `disk`;");
			} else {
				state = con.prepareStatement(getSearchAlgoritmCount);
				state.setString(2, search);
				state.setString(1, search);
				state.setString(3, search);
				resSet = state.executeQuery();
			}
			int count = 0;
			if (resSet.next())
				count = resSet.getInt(1);
			System.out.println("found" + count);
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<Disk> getFullDiskList(String search, int elem) {

		List<Disk> DiskList = new ArrayList<Disk>();
		System.out.println(elem);
		try {
			con = super.getDBConnection();

			if (search == null || search.isEmpty()) {

				state = con.prepareStatement("select * from disk limit ?, 10");
				state.setInt(1, elem);
				resSet = state.executeQuery();
			} else {
				state = con.prepareStatement(getSearchAlgoritm);
				state.setInt(4, elem);
				state.setString(1, search);
				state.setString(2, search);
				state.setString(3, search);
				resSet = state.executeQuery();
			}
			while (resSet.next()) {
				Disk disk = fillDisk(resSet);

				Actor actor = ActorDAO.getActorByDiskId(disk.getId(), con);

				disk.setActors(actor);

				Genre genre = GenreDAO.getGenreByDiskId(disk.getId(), con);

				disk.setGenre(genre);

				DiskList.add(disk);
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return null;
		}

		return DiskList;
	}

	public Disk getDiskById(int id, boolean withActors, boolean withGenres) {

		try {
			con = super.getDBConnection();
			state = con.prepareStatement(diskById);
			state.setInt(1, id);
			ResultSet resSet = state.executeQuery();

			if (resSet.next()) {
				Disk disk = fillDisk(resSet);

				if (withActors == true) {
					Actor actor = ActorDAO.getActorByDiskId(id, con);
					disk.setActors(actor);
				}

				if (withGenres == true) {
					Genre genre = GenreDAO.getGenreByDiskId(id, con);
					disk.setGenre(genre);
				}

				return disk;
			}
			state.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private Disk fillDisk(ResultSet resSet) throws SQLException {
		Disk disk = new Disk();
		disk.setId(resSet.getInt(1));
		disk.setSellable(resSet.getByte(3));
		disk.setTitle(resSet.getString(2));
		disk.setReleaseDate(resSet.getInt(4));
		disk.setPrice(resSet.getFloat(5));
		disk.setStored(resSet.getInt(6));
		disk.setDescription(resSet.getString(7));
		disk.setCover(resSet.getString(8));
		disk.setRating(resSet.getString(9));
		return disk;
	}

	@Override
	protected void finalize() throws Throwable {
		con.close();
		// TODO Auto-generated method stub
		super.finalize();
	}

}
