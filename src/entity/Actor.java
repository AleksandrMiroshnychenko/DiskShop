package entity;

import java.util.List;

public class Actor extends Disk{

	private int id;
	private List<Integer> actorIdBatch;
    private List<String> actorNameBatch;
    private String actorName;

    public Actor(){
    	
    }
    
    public Actor(List<String> actorNameBatch, String actorName) {
		this.setActorNameBatch(actorNameBatch);
		this.setActorName(actorName);
	}
    
    public Actor(List<String> actorNameBatch) {
		this.setActorNameBatch(actorNameBatch);
	}
    
    public Actor(String existed) {
    	this.setActorName(existed);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getActorNameBatch() {
		return actorNameBatch;
	}

	public void setActorNameBatch(List<String> actorNameBatch) {
		this.actorNameBatch = actorNameBatch;
	}


	public List<Integer> getActorIdBatch() {
		return actorIdBatch;
	}

	public void setActorIdBatch(List<Integer> actorIdBatch) {
		this.actorIdBatch = actorIdBatch;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

   
    
	
}
