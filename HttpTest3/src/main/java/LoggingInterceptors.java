import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.logging.Logger;

public final class LoggingInterceptors {

    private static final Logger logger = Logger.getLogger(LoggingInterceptors.class.getName());

    private final OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(new LoggingInterceptor())
            .addInterceptor(new LoggingInterceptor2())
            .addInterceptor(new LoggingInterceptor3())
            .build();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        response.body().close();
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long t1 = System.nanoTime();
            Request request = chain.request();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    request.url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }
    private static class LoggingInterceptor2 implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long t1 = System.nanoTime();
            Request request = chain.request();

            request  =request.newBuilder().addHeader("request2","white2").build();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));


            Response response = chain.proceed(request);
            response.header("Accept-Ranges","black2");
            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    request.url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }
    private static class LoggingInterceptor3 implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long t1 = System.nanoTime();
            Request request = chain.request();

            request  =request.newBuilder().addHeader("request3","white3").build();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            response.header("Accept-Ranges","black3");
            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    request.url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

    public static void main(String... args) throws Exception {
        new LoggingInterceptors().run();
    }
}
