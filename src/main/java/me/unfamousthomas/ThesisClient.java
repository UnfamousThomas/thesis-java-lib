package me.unfamousthomas;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import me.unfamousthomas.models.DeleteInfoModel;
import me.unfamousthomas.models.ShutdownInfoModel;
import me.unfamousthomas.utilities.JsonHandler;

public class ThesisClient {

  private final HttpClient client;
  private final String baseUrl;

  protected ThesisClient(HttpClient client, String baseUrl) {
    this.client = client;
    this.baseUrl = baseUrl;
  }

  /**
   * Asynchronously asks if delete is currently allowed
   * @return A future which contains the response
   */
  public CompletableFuture<HttpResponse<DeleteInfoModel>> GetDeleteAllowed() {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + "/allow_delete"))
        .GET()
        .build();

    return client.sendAsync(request, JsonHandler.ofModel(DeleteInfoModel.class));
  }

  /**
   * Asynchronously sets delete to be allowed
   * @return A future which contains the new delete state
   */
  public CompletableFuture<HttpResponse<DeleteInfoModel>> SetDeleteAllowed(DeleteInfoModel deleteInfo) {
    String requestBody = JsonHandler.toJson(deleteInfo);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + "/allow_delete"))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build();

    return client.sendAsync(request, JsonHandler.ofModel(DeleteInfoModel.class));
  }


  /**
   * Asynchronously asks if shutdown is currently requested.
   * <bold>Note: We do not allowed you to set shutdown requested, as that is done by the cluster when a request is sent.</bold>
   * This method is used to check if the current server is in a queue to be deleted, so that we can act accordingly.
   * @return A future which contains the shutdown state
   */

  public CompletableFuture<HttpResponse<ShutdownInfoModel>> GetShutdownRequested() {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + "/shutdown"))
        .GET()
        .build();

    return client.sendAsync(request, JsonHandler.ofModel(ShutdownInfoModel.class));
  }
}
