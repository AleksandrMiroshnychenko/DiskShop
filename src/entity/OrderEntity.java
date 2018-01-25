package entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class OrderEntity {

	private int id;
	private float customPrice;
	private String name;
	private String phone;
	private byte sendable;
	private Date confirmDate;
	private Date cancelDate;
	private String email;
	private Integer userId;
	private List<CartFactory> cart;
	private Delivery delivery;
	private Date updateDate;
	private Date earnDate;

	public List<CartFactory> getCart() {
		return cart;
	}
	
	public String getFormattedUpdateDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(updateDate);
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	public String getFormattedCancelDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(cancelDate);
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	public String getFormattedEarnDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(earnDate);
		}
		catch(NullPointerException e){
			return null;
		}
	}

	public String getFormattedConfirmDate() {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(confirmDate);
		}catch(NullPointerException e){
			return null;
		}
		
	}

	public void setCart(List<CartFactory> cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCustomPrice() {
		return customPrice;
	}

	public void setCustomPrice(float customPrice) {
		this.customPrice = customPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getSendable() {
		return sendable;
	}

	public void setSendable(byte sendable) {
		this.sendable = sendable;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		try {
			return userId;
		} catch (NullPointerException e) {}
		return null;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@SuppressWarnings("unchecked")
	public OrderEntity createOrder(HttpServletRequest request) {
		customPrice = 0;
		name =  request.getParameter("lastname").trim();
		name = name.concat(" ".concat(request.getParameter("firstname").trim()));
		name = name.concat(" ".concat(request.getParameter("surname").trim()));
		phone = request.getParameter("phone");
		email = request.getParameter("email");

		try {

			User user = (User) request.getSession().getAttribute("user");
			userId = user.getUserId();
		} catch (NumberFormatException | NullPointerException e) {
			userId = null;
		}

		try {
			sendable = Byte.valueOf(request.getParameter("sendable"));
		} catch (NumberFormatException e) {
			sendable = 0;
		}
		HashMap<Integer, CartFactory> temp = (HashMap<Integer, CartFactory>) request.getSession(false)
				.getAttribute("cart");

		cart = new ArrayList<CartFactory>(temp.values());

		for (CartFactory fac : cart) {
			customPrice += fac.getPrice();
		}

		if (sendable == 1) {
			setDelivery(new Delivery().addDelivery(request));
			customPrice += CostConstants.deliveryPrice;
		}

		return this;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Date getUpdateDate() {
		
		//SimpleDateFormat form = new SimpleDateFormat("HH:mm dd-MM-yy");
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getEarnDate() {
		return earnDate;
	}

	public void setEarnDate(Date earnDate) {
		this.earnDate = earnDate;
	}

}



