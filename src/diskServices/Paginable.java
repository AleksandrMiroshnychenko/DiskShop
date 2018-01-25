package diskServices;

public abstract class Paginable {
	
	static int getPage(int num, int size){
		
		int lastIn;
		
		int count = getPagecount(size);
		
		num--;
		
		if(num==count-1){
			lastIn = size - num*10;
		}
		else if(size<=10) lastIn = size - num*10;
		
		else lastIn = 10;
					
		
		return lastIn;
		
	}
	
	public static int getElemCount(int page){
		page--;
		
		page *=10;
		
		return page;
	}
	
	
	
	public static int getPagecount(int size){		
		
		int count;
		if(size%10!=0&&size>10){
			count = size/10 + 1;
		}
		else if(size<10) count=1;
		else count = size/10;
		
		return count;
		
	}
	
}
