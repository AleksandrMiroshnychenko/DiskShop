package entity;

public enum Rating {G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");
	
	 	Rating(String realName) {
    this.realName = realName;
}
public String getRealName() {
    return realName;
}
private final String realName;

}
