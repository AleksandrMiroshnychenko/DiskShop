package entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Disk {

	private int id;
	private String title;
	private byte sellable;
	private String isbn;
	private int stored;
	private float price;
	private String description;
	private int release;
	private Genre genre;
	private Actor actors;
	private String cover;
	private Rating rating;

	public Disk(String title, byte sellable, float price, int stored) {
		this.title = title;
		this.sellable = sellable;
		this.stored = stored;
		this.price = price;
	}

	public Disk(String title, byte sellable, float price, int stored, String isbn, int release, String description,
			Genre genre, Actor actor) {
		this.title = title;
		this.sellable = sellable;
		this.stored = stored;
		this.price = price;
		if (isbn.isEmpty())
			this.isbn = null;
		else
			this.isbn = isbn;

		this.release = release;
		if (description.isEmpty())
			this.description = null;
		else
			this.description = description;

		this.setGenre(genre);
		this.actors = actor;
	}

	public Disk createDisk(HttpServletRequest request) {

		try {
			this.title = request.getParameter("title");
			this.price = Float.valueOf(request.getParameter("price"));
			this.stored = Integer.valueOf(request.getParameter("stored"));
			this.release = Integer.valueOf(request.getParameter("release"));
			this.description = request.getParameter("description");
			if (description.isEmpty()) {
				description = null;
			}

			try {
				this.id = Integer.valueOf(request.getParameter("id"));
			} catch (NullPointerException | NumberFormatException e) {
				this.sellable = 0;
			}
			this.cover = null;
			this.cover = request.getParameter("cover");
			if (!cover.isEmpty())
				this.cover = "covers/" + cover;

			try {
				this.sellable = Byte.valueOf(request.getParameter("sellable"));
			} catch (NullPointerException | NumberFormatException e) {
				this.sellable = 0;
			}

			this.rating = Rating.valueOf(request.getParameter("rating"));

		} catch (NullPointerException | NumberFormatException e) {
			return null;
		}
		return this;
	}

	public Disk() {
	}

	public byte getSellable() {
		return sellable;
	}

	public void setSellable(byte sellable) {
		this.sellable = sellable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStored() {
		return stored;
	}

	public void setStored(int stored) {
		this.stored = stored;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setDescription(String descr) {
		description = descr;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return "Disk: ID: " + id + " Title: " + title + " Price: " + price + " Stored on: " + stored + " ISBN: " + isbn;
	}

	public int getReleaseDate() {
		return release;
	}

	public void setReleaseDate(int releaseDate) {
		release = releaseDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Actor getActors() {
		return actors;
	}

	public void setActors(Actor actors) {
		this.actors = actors;
	}

	/*
	 * public Disk getDiskBiId(int id, List<Disk> disks){
	 * 
	 * for (Disk disk : disks) { if(disk.getId()==id) return disk; }
	 * 
	 * return null; }
	 */

	static List<Integer> stringToDisk(String[] var) {
		List<Integer> convert = new ArrayList<>();
		int iter = 0;
		try {
			for (String string : var) {
				convert.add(Integer.valueOf(string));
				System.out.println("Success " + convert.get(iter));
				iter++;
			}

		} catch (NullPointerException e) {
		}
		return convert;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Rating getRating() {
		return rating;
	}

	public String getPrintRating() {
		return rating.getRealName();
	}

	public void setRating(String rating) {
		this.rating = Rating.valueOf(rating);
	}

}
