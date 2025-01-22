package me.unfamousthomas.utilities;

import com.google.gson.Gson;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class JsonHandler {
  public static <T> HttpResponse.BodyHandler<T> ofModel(Class<T> modelClass) {
    return responseInfo -> HttpResponse.BodySubscribers.mapping(
        HttpResponse.BodySubscribers.ofInputStream(),
        inputStream -> {
          try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
            String json = scanner.hasNext() ? scanner.next() : "";
            Gson gson = new Gson();
            return gson.fromJson(json, modelClass);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
    );
  }

  public static String toJson(Object object) {
    Gson gson = new Gson();
    return gson.toJson(object);
  }
}
