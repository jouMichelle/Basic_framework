package ai.dongsheng.common;


public class Session {
	private static ThreadLocal<String> session = new ThreadLocal<String>();
	
	public static void set(String session){
		Session.session.set(session);
	}

	public static String get() {
		return Session.session.get();
	}

	public static void clear(){
		Session.session.set(null);
	}
}
