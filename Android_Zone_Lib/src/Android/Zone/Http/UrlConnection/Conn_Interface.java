package Android.Zone.Http.UrlConnection;

import java.net.HttpURLConnection;
import Android.Zone.Http.UrlConnection.Conn_Core.HttpType;

public interface Conn_Interface {
	public abstract HttpType getHttpType();
	public abstract String getHttpUrl();
	public abstract void connInit(HttpURLConnection conn);
	public abstract void writeConn(HttpURLConnection conn);
	public abstract void setEncoding(String encoding);

}
