package main;

public class Logger {
	private String canonical_class;
	
	public Logger(Class<?> cls){
		this.canonical_class = cls.getCanonicalName();
	}
	
	public void info(String msg) {
		String full_msg= "[INFO] "+canonical_class+" : "+msg;
		System.out.println(full_msg);
		SimpleUI.writeLog(full_msg);
	}
	public void warn(String msg) {
		String full_msg= "[WARN] "+canonical_class+" : "+msg;
		System.out.println(full_msg);
		SimpleUI.writeLog(full_msg);
	}
	public void error(String msg) {
		String full_msg= "[ERROR] "+canonical_class+" : "+msg;
		System.err.println(full_msg);
		SimpleUI.writeLog(full_msg);
	}
	public void info(Object msg) {
		info(msg.toString());
	}
	public void warn(Object msg) {
		warn(msg.toString());
	}
	public void error(Object msg) {
		error(msg.toString());
	}
}