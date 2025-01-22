package me.unfamousthomas;

import java.net.http.HttpClient;
import lombok.NonNull;
import me.unfamousthomas.utilities.ProtocolType;

/**
 * Builder used to create a new {@link ThesisClient}
 */
public class ThesisClientBuilder {

  private int port = 8080;
  private ProtocolType protocol = ProtocolType.HTTP;

  /**
   * In case your sidecar runs on a different port from default
   * @param port What port yours runs on
   * @return the builder
   */
  public ThesisClientBuilder port(int port) {
    this.port = port;
    return this;
  }

  /**
   * In case your sidecar runs on https
   * @param protocol What protocol yours runs on (currently HTTP/HTTPS)
   * @return the builder
   */
  public ThesisClientBuilder protocol(@NonNull ProtocolType protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * Builds the ThesisClient from a http client and a url
   * @return a client used to run requests
   */
  public ThesisClient build() {
    String url = protocol.getProtocol() + "://localhost:" + port;
    return new ThesisClient(HttpClient.newHttpClient(), url);
  }

}
