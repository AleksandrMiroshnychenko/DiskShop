package dao;

public interface Queries {

	static final String addDisk = "INSERT INTO disk"
			+ "(title, sellable, `release`, price, `stored`, description, cover, rating, id ) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	public static final String updateDisk = "UPDATE `disk` SET "
			+ "title = ?, sellable = ?, `release` = ?, price = ?,"
			+ " `stored` = ?, description = ?, cover = ?, rating = ?  where `disk`.`id` = ?;";
	
	

	static final String addUser = "INSERT INTO users" + "(nickname, password, mail, reg_date, role) "
			+ "VALUES(?, ?, ?, ?, 0);";

	static final String addOrder = "insert into `order` (name, phone, mail, sendable, custom_price, users_id) "
			+ "values (?, ?, ?, ?, ?, ?);";

	static final String diskHasReturn = "UPDATE `order_disk` SET `return_date` =  current_date()"
			+ " where order_id = ? and disk_id = ?;";
	
	
	public static final String addDelivery = "insert into `delivery` "
			+ "(order_id, region, city, address, post_index) values (LAST_INSERT_ID(),?,?,?,?);";

	public static final String countOrderedDisks = "UPDATE `disk`, `order_disk`, `order` "
			+ "SET `stored` =  `stored` - `count` WHERE `order_id` = ? and `disk_id` = `disk`.`id`;";

	public static final String relate_order_disk = "insert into `order_disk`"
			+ "(order_id, disk_id, hire_end, order_disk.count) values (LAST_INSERT_ID(), ?, ?, ?);";

	static final String userById = "select * from users where id = ?;";

	static final String diskById = "select * from disk where id = ?;";

	static final String diskPriceById = "select price from disk where id = ?;";

	static final String addGenre = "insert into genre (name, id) values (?,?);";

	static final String relate_disk_genre = "insert into disk_genre (disk_id, genre_id) values (?,?);";

	static final String relate_disk_actor = "insert into disk_actor (disk_id, actor_id) values (?,?);";

	static final String addActor = "insert into actor (id, name) values (?,?);";

	static final String checkUniqueDisk = "select b.title from disk b, actor a, disk_actor ba "
			+ "where ba.disk_id = b.id and a.id = ba.actor_id and (b.title= ? and "
			+ "a.name = ? and b.`release` = ?);";

	static final String getGenreRelatedWithDisk = "select distinct genre.id, genre.name from disk, disk_genre, genre "
			+ "where disk_genre.disk_id = disk.id and disk_genre.genre_id = genre.id  and disk.id = ?;";

	static final String getActorRelatedWithDisk = "select distinct actor.id, actor.name from disk, actor, disk_actor "
			+ "where disk_actor.actor_id = actor.id and disk_actor.disk_id = disk.id and disk.id = ?;";

	static final String getDelivery = "select distinct region, delivery.city, delivery.address, delivery.post_index"
			+ ", delivery.request_date from delivery, `order` "
			+ "where delivery.order_id = `order`.id and `order`.id = ?;";

	static final String getOrderRelatedWithDisk = "select b.id, b.title, bo.return_date, bo.hire_end, bo.count from"
			+ " disk b, `order` o, order_disk bo where b.id = bo.disk_id and o.id = bo.order_id and o.id = ?;";

	static final String getSearchAlgoritm = "select DISTINCT * from (select disk.* from disk, genre, disk_genre, disk_actor, actor"
			+ "  where disk_actor.disk_id=disk.id and disk_genre.disk_id=disk.id and disk_actor.actor_id = actor.id and"
			+ " disk_genre.genre_id=genre.id and (locate(?, disk.title) or locate(?, actor.name) or locate(?, genre.name)))"
			+ " as disk limit ?,10;";

	static final String getSearchAlgoritmCount = "select count(disk.id) from disk, genre, disk_genre, "
			+ "disk_actor, actor WHERE disk_actor.disk_id=disk.id and disk_genre.disk_id=disk.id "
			+ "and disk_actor.actor_id = actor.id and disk_genre.genre_id=genre.id "
			+ "and (CONTAINS(disk.title, ?) or CONTAINS(actor.name, ?) or CONTAINS (genre.name, ?)); ";
	
	static final String optionOrdered = "{call optionOrder(?, ?)}";
	

	
}
