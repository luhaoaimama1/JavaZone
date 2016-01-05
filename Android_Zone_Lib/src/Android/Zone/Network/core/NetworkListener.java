package Android.Zone.Network.core;

public interface NetworkListener {
	public abstract void onStart();

	public abstract void onCancelled();

	public abstract void onLoading(long total, long current, boolean isUploading);

	public abstract void onSuccess(String msg);

	public abstract void onFailure( String msg);
}
