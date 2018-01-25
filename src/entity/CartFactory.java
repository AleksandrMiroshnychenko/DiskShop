package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import dao.DiskDAO;

public class CartFactory {

	private int id;
	private int count;
	private Date date;
	private float price;
	private String title;
	private Date factReturnDate;

	public String getFormattedDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
		} 
		catch(NullPointerException e){
			return null;
		}
	}
	
	public String getFormattedFactReturnDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(factReturnDate);
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@SuppressWarnings("deprecation")
	public CartFactory addCart(HttpServletRequest request) {

		count = Integer.valueOf(request.getParameter("countOrderedDisk"));

		int days;

		String stat = request.getParameter("status");
		if (stat.equals("0")) {
			days = Integer.valueOf(request.getParameter("hireDuration"));

			date = new Date(System.currentTimeMillis());
			date.setDate(date.getDate() + days);
		} else {
			days = 0;
			date = null;
		}

		int id = Integer.valueOf(request.getParameter("diskId"));

		System.out.println(id);
		Disk disk = new DiskDAO().getDiskById(id, false, false);

		byte status = disk.getSellable();

		System.out.println(status);

		System.out.println(days);

		if (status == 1 && days != 0) {
			return null;
		}

		if (status == 0 && days == 0) {
			return null;
		}

		price = disk.getPrice();
		this.title = disk.getTitle();

		if (days != 0) {
			price = (float) (days * CostConstants.pricePerDay);
		}

		price = price * count;

		this.id = id;

		return this;

	}

	public float getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFactReturnDate() {
		return factReturnDate;
	}

	public void setFactReturnDate(Date factReturnDate) {
		this.factReturnDate = factReturnDate;
	}

}
