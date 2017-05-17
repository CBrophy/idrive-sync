package org.granite.cloud.sync.idrive;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dagger.ObjectGraph;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.granite.base.ExceptionTools;
import org.granite.cloud.sync.idrive.api.GetServerAddressRequest;
import org.granite.cloud.sync.idrive.api.GetServerAddressResponse;
import org.granite.configuration.*;

import java.io.IOException;
import java.util.logging.Logger;
import org.granite.log.LogTools;

public class SyncMain {

  private static final String DEFAULT_LOG_FORMAT = "[%1$tc] %4$s: %5$s %n";
  private final ApplicationConfiguration applicationConfiguration;
  private final OkHttpClient httpClient = new OkHttpClient();

  @Inject
  SyncMain(
      final ApplicationConfiguration applicationConfiguration
  ) {
    this.applicationConfiguration = checkNotNull(applicationConfiguration,
        "applicationConfiguration");
  }

  public static void main(final String[] args) {
    // see doc for java.util.logging.SimpleFormatter
    // log output will look like:
    // [Tue Dec 16 10:29:07 PST 2014] INFO: <message>
    System.setProperty("java.util.logging.SimpleFormatter.format", DEFAULT_LOG_FORMAT);

    String externalConfig = args != null && args.length > 0 ? args[0].trim() : null;

    ObjectGraph.create(SyncModule.class).get(SyncMain.class).run();

  }

  private void run() {

    final GetServerAddressRequest getServerAddressRequest = new GetServerAddressRequest(
        applicationConfiguration.getString("Username"),
        applicationConfiguration.getString("Password"),
        applicationConfiguration.getString("EvsHost"),
        applicationConfiguration.getString("EvsPathRoot"),
        applicationConfiguration.getString("EvsRedirectEndpoint")
    );

    final GetServerAddressResponse response = getServerAddressRequest
        .request(httpClient);

    System.out.print(response.getMessage());
  }
}
