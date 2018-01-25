package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Actor;

public class ActorDAO extends DAOFactory implements Queries {

	static PreparedStatement pstat;
	Statement stat;
	ResultSet res;

	public ActorDAO() {
	}

	public void addActorBatch(Actor entity, Connection con, int currentDiskId, List<Integer>exActor) {
		try {
			
			pstat = con.prepareStatement(relate_disk_actor);

			for (Integer integer : exActor) {
				pstat.setInt(1, currentDiskId);
				pstat.setInt(2, integer);
				pstat.addBatch();
			}
			pstat.executeBatch();

			CallableStatement cstat = con.prepareCall("{call exActor(?, ?)}");
			for (String string : entity.getActorNameBatch()) {
				cstat.setInt(1, currentDiskId);
				cstat.setString(2, string);
				cstat.addBatch();
			}
				cstat.executeBatch();

		} catch (Exception e) {}
	}

	public ArrayList<Actor> getActorNames() {

		ArrayList<Actor> Actors = new ArrayList<Actor>();
		ResultSet res;
		try {
			Connection con = super.getDBConnection();
			stat = con.createStatement();
			res = stat.executeQuery("select id, name from actor;");

			while (res.next()) {
				Actor actor = new Actor(res.getString(2));
				actor.setId(res.getInt(1));
				Actors.add(actor);
			}

			res.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return Actors;
	}
	
	static Actor getActorByDiskId(int id, Connection con){
		
		Actor actor = new Actor();
		List<String> actorNameBatch = new ArrayList<String>();
		List<Integer> actorIdBatch = new ArrayList<Integer>();

		try {
			pstat = con.prepareStatement(getActorRelatedWithDisk);
			pstat.setInt(1, id);
			ResultSet res = pstat.executeQuery();
			while (res.next()) {

				actorIdBatch.add(res.getInt(1));
				actorNameBatch.add(res.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		actor.setActorIdBatch(actorIdBatch);
		actor.setActorNameBatch(actorNameBatch);
		
		
		return actor;
		
	}

}
