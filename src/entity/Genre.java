package entity;

import java.util.List;

public class Genre extends Disk{

	private String genrename;
	private List<String> genreBatch;
	int genreid;
	private List<Integer> genreIdBatch;

	public Genre() {

	}

	public int getId() {
		return genreid;
	}

	public void setId(int id) {
		this.genreid = id;
	}

	public String getName() {
		return genrename;
	}

	public void setName(String name) {
		this.genrename = name;
	}

	/*public void appendIntoDB() {
		try {

			new GenreDAO().addGenre(this);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public List<String> getGenreBatch() {
		return genreBatch;
	}

	public void setGenreBatch(List<String> genreBatch) {
		this.genreBatch = genreBatch;
	}

	public List<Integer> getGenreIdBatch() {
		return genreIdBatch;
	}

	public void setGenreIdBatch(List<Integer> genreIdBatch) {
		this.genreIdBatch = genreIdBatch;
	}
}
