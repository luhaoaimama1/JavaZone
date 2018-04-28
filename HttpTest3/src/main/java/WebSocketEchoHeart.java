
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.WebSocket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class WebSocketEchoHeart extends WebSocketListener {
    private void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://echo.websocket.org")
                .build();
        client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        webSocket.send("Hello...");
        webSocket.send("...World!");
        webSocket.send(ByteString.decodeHex("deadbeef"));
//        webSocket.close(1000, "Goodbye, World!");
        Timer timer = new Timer();
        //收到服务器端发送来的信息后，每隔25秒发送一次心跳包
        final String message = "{\"type\":\"heartbeat\",\"user_id\":\"heartbeat\"}";
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                webSocket.send(message);
            }
        },1000,2500);
    }

    @Override
    public void onMessage(final WebSocket webSocket, String text) {
        System.out.println("MESSAGE: " + text);

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("MESSAGE: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        System.out.println("CLOSE: " + code + " " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }

    public static void main(String... args) {
        new WebSocketEchoHeart().run();
    }
}
