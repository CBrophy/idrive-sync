package org.granite.cloud.sync.idrive.api;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.logging.Logger;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.granite.base.ExceptionTools;
import org.granite.log.LogTools;

/**
 * Created by cbrophy on 5/16/17.
 */
public class GetServerAddressRequest {
  private final String uid;
  private final String pwd;
  private final String host;
  private final String path;
  private final String redirectEndpoint;

  public GetServerAddressRequest(
      String uid,
      String pwd,
      String host,
      String path,
      String redirectEndpoint) {
    this.uid = uid;
    this.pwd = pwd;
    this.host = host;
    this.path = path;
    this.redirectEndpoint = redirectEndpoint;
  }

  public GetServerAddressResponse request(final OkHttpClient httpClient){
    HttpUrl httpUrl = new HttpUrl
        .Builder()
        .scheme("https")
        .host(host)
        .addPathSegment(path)
        .addPathSegment(redirectEndpoint)
        .build();

    final RequestBody requestBody = new FormBody.Builder()
        .add("uid", uid)
        .add("pwd", pwd)
        .build();

    final Request redirectRequest = new Request.Builder()
        .url(httpUrl)
        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
        .post(requestBody)
        .build();

    try {
      final Response response = httpClient.newCall(redirectRequest).execute();

      final XmlMapper xmlMapper = new XmlMapper();

      checkNotNull(response.body(), "Response body is null");

      return xmlMapper.readValue(response.body().bytes(), GetServerAddressResponse.class);
    } catch (IOException e) {
      throw ExceptionTools.checkedToRuntime(e);
    }
  }
}
