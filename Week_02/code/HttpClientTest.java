import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * @author 
 * @title: HttpClientTest
 * @projectName 23_env_config_proxy
 * @description:
 * @date 2021/1/22 20:01
 */
public class HttpClientTest {
    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(600, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url("http://localhost:8801")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            System.out.println(response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
