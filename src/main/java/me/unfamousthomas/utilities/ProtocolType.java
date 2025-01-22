package me.unfamousthomas.utilities;

import lombok.Getter;

/**
 * A simple enum to represent HTTP and HTTPS.
 */
@Getter
public enum ProtocolType {

  HTTP("http"),
  HTTPS("https");

  private final String protocol;

  ProtocolType(String protocol) {
    this.protocol = protocol;
  }
}
