package org.granite.cloud.sync.idrive.api;

public class GetServerAddressResponse {
  private String message;
  private String cmdUtilityServer;
  private String cmdUtilityServerIP;
  private String webApiServer;
  private String webApiServerIP;

  GetServerAddressResponse(){}

  public String getMessage() {
    return message;
  }

  public String getCmdUtilityServer() {
    return cmdUtilityServer;
  }

  public String getCmdUtilityServerIP() {
    return cmdUtilityServerIP;
  }

  public String getWebApiServer() {
    return webApiServer;
  }

  public String getWebApiServerIP() {
    return webApiServerIP;
  }
}
