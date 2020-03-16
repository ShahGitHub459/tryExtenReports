package DriverFactory;

public class DriverManagerFactory {
	public enum DriverType{
		CHROME,FIREFOX,EDGE,IE;
	}
	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		switch (type) {
		case CHROME:
			driverManager=new ChromeDriverManager();
			break;
		case FIREFOX:
			driverManager=new FireFoxDriverManager();
			break;
		default:
			driverManager=new FireFoxDriverManager();
			break;
		}
		return driverManager;
	}
}
