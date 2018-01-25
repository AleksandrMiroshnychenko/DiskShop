package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Actor;
import entity.Disk;
import entity.CartFactory;
import entity.Delivery;
import entity.Genre;
import entity.OrderEntity;
import userPages.Cart;
import userPages.Order;

public class OrderDao extends DAOFactory implements Queries {

	private Connection con;
	private PreparedStatement state;
	private ResultSet resSet;
	// public Boolean check = false;

	public OrderDao() {
		try {
			con = super.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addOrder(OrderEntity entity) {
		try {

			// int id = 0;
			state = con.prepareStatement(addOrder);

			state.setString(1, entity.getName());
			state.setString(2, entity.getPhone());
			state.setString(3, entity.getEmail());
			state.setByte(4, entity.getSendable());
			state.setFloat(5, entity.getCustomPrice());
			try {
				state.setInt(6, entity.getUserId());
			} catch (NullPointerException e) {
				state.setNull(6, java.sql.Types.INTEGER);
			}

			state.executeUpdate();

			Delivery del = entity.getDelivery();
			if (del != null) {
				state = con.prepareStatement(addDelivery);

				state.setString(1, del.getRegion());
				state.setString(2, del.getCity());
				state.setString(3, del.getAddress());
				state.setInt(4, del.getPostIndex());
				// state.setByte(5, del.getDelivered());
				// state.setInt(5, entity.getUserId());
				state.executeUpdate();
			}

			List<CartFactory> cart = entity.getCart();

			state = con.prepareStatement(relate_order_disk);

			for (CartFactory cartFactory : cart) {

				// state.setInt(1, entity.getId());
				state.setInt(1, cartFactory.getId());
				state.setDate(2, cartFactory.getDate());
				state.setInt(3, cartFactory.getCount());
				state.addBatch();
			}
			state.executeBatch();

			con.commit();

			state.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getOrderCount(boolean confirmed) {
		try {
			con = super.getDBConnection();
			Statement stat = con.createStatement();
			ResultSet resSet;
			if (confirmed == true) {
				resSet = stat.executeQuery("select count(order.id) from `order`;");
			} else {
				resSet = stat.executeQuery("select count(order.id) from `order` where confirmed is null;");
			}
			int count = 0;
			if (resSet.next())
				count = resSet.getInt(1);
			stat.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<OrderEntity> getFullOrderList(boolean confirmed, int page) {

		List<OrderEntity> OrderList = new ArrayList<OrderEntity>();
		try {

			if (confirmed == true) {
				state = con.prepareStatement("select * from `order` order by updated desc limit ?, 10");

			} else {
				state = con.prepareStatement(
						"select * from `order` where earned is null order by updated desc limit ?, 10");
			}
			state.setInt(1, page);
			resSet = state.executeQuery();
			while (resSet.next()) {
				OrderEntity entity = fillOrder(resSet);

				Delivery delivery = getDeliveryByOrderId(entity.getId(), con);

				entity.setDelivery(delivery);

				List<CartFactory> cart = getBookRelatedWithOrder(con, entity.getId());

				entity.setCart(cart);

				OrderList.add(entity);
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return null;
		}

		return OrderList;
	}

	private OrderEntity fillOrder(ResultSet resSet) throws SQLException {

		OrderEntity entity = new OrderEntity();
		entity.setId(resSet.getInt(1));
		entity.setSendable(resSet.getByte(2));
		entity.setCustomPrice(resSet.getFloat(3));
		entity.setName(resSet.getString(4));
		entity.setPhone(resSet.getString(5));
		entity.setEmail(resSet.getString(6));
		entity.setConfirmDate(resSet.getDate(11));
		entity.setCancelDate(resSet.getDate(8));
		entity.setEarnDate(resSet.getDate(10));
		entity.setUserId(resSet.getInt(9));
		entity.setUpdateDate(resSet.getDate(7));
		return entity;
	}

	private Delivery getDeliveryByOrderId(int id, Connection con) {

		Delivery delivery = new Delivery();
		PreparedStatement state;

		try {
			state = con.prepareStatement(getDelivery);
			state.setInt(1, id);

			ResultSet res = state.executeQuery();
			if (res.next()) {
				delivery.setRegion(res.getString(1));
				delivery.setCity(res.getString(2));
				delivery.setAddress(res.getString(3));
				delivery.setPostIndex(res.getInt(4));
				delivery.setRequestDate(res.getDate(5));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return delivery;
	}

	private List<CartFactory> getBookRelatedWithOrder(Connection con, int id) throws SQLException {

		List<CartFactory> cart = new ArrayList<CartFactory>();
		PreparedStatement state;
		ResultSet resSet;
			state = con.prepareStatement(getOrderRelatedWithDisk);
			state.setInt(1, id);
			resSet = state.executeQuery();

			while (resSet.next()) {
				CartFactory factory = new CartFactory();
				factory.setId(resSet.getInt(1));
				factory.setTitle(resSet.getString(2));
				factory.setFactReturnDate(resSet.getDate(3));
				factory.setDate(resSet.getDate(4));
				factory.setCount(resSet.getInt(5));
				cart.add(factory);
			}


		return cart;
	}

	public void changeOrderStatus(int id, Byte option) {

		
		try {
			CallableStatement state = con.prepareCall(optionOrdered);
			state.setInt(1, option);
			state.setInt(2, id);
			state.executeUpdate();
			con.commit();
			System.out.println("успешная смена статуса");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkReturnDate(int orderId, int bookId) {
		
		
		try {
			con = super.getDBConnection();
			PreparedStatement state;
			state = con.prepareStatement(diskHasReturn);
			state.setInt(1, orderId);
			state.setInt(2, bookId);
			state.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public byte checkOrderById(int id) {
		
		try {
		con = super.getDBConnection();
		PreparedStatement state;

			state = con.prepareStatement(
					"select id from (select * from `order` limit 0, 1 )"
					+ " as `order` where users_id = ?");
		
		state.setInt(1, id);

		ResultSet resSet = state.executeQuery();
		if (resSet.next()) {
			return -1;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	

	public List<OrderEntity> getOrderById(int id) {
		List<OrderEntity> OrderList = new ArrayList<OrderEntity>();
		try {
		con = super.getDBConnection();
		PreparedStatement state;

			state = con.prepareStatement(
					"select * from (select * from `order` order by updated desc limit 0, 15 )"
					+ " as `order` where users_id = ?");
		
		state.setInt(1, id);

		ResultSet resSet = state.executeQuery();
		while (resSet.next()) {

			OrderEntity entity = fillOrder(resSet);
			Delivery delivery = getDeliveryByOrderId(entity.getId(), con);

			entity.setDelivery(delivery);

			List<CartFactory> cart = getBookRelatedWithOrder(con, entity.getId());

			entity.setCart(cart);
			OrderList.add(entity);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return OrderList;

	}

	@Override
	protected void finalize() throws Throwable {
		con.close();
		// TODO Auto-generated method stub
		super.finalize();
	}

}
