import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {
  OkHttpClient client = new OkHttpClient();

  String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public static void main(String[] args) throws IOException {
//    GetExample example = new GetExample();
////    String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
//    String response = example.run("https://www.baidu.com");
//    System.out.println(response);
    String s = Files.probeContentType(Paths.get("/Users/fuzhipeng/Desktop/h5config/h5/index.html"));
    System.out.println(s);
  }
}
