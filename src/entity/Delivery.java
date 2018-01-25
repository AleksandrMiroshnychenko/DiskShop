package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class Delivery implements CostConstants {
private Date requestDate;
private String region;
private String city;
private String address;
private int postIndex;

public Date getRequestDate() {
	return requestDate;
}
public void setRequestDate(Date requestDate) {
	this.requestDate = requestDate;
}

public String getFormattedRequestDate() {
	try{
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	return sdf.format(requestDate);
	}
	catch(NullPointerException e){
		return null;
	}
}

public String getRegion() {
	return region;
}
public void setRegion(String region) {
	this.region = region;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getPostIndex() {
	return postIndex;
}
public void setPostIndex(int postIndex) {
	this.postIndex = postIndex;
}


public float getDeliveryPrice(){
	return deliveryPrice;
}


	public Delivery addDelivery(HttpServletRequest request) {
		
		//delivered;
		//requestDate;
		//responseDate;
		region = request.getParameter("region");
		city = request.getParameter("city");
		address = request.getParameter("address");
		postIndex = Integer.valueOf(request.getParameter("postIndex"));
		
		return this;
		
	}


}
