package org.granite.cloud.sync.idrive.api;

import static org.junit.Assert.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import org.junit.Test;

public class GetServerAddressResponseTest {

  @Test
  public void testDeserialization() throws IOException {
    final String responseXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<tree message=\"SUCCESS\" cmdUtilityServer=\"evs1226.idrive.com\"\n"
        + "    cmdUtilityServerIP=\"66.51.30.112\"\n"
        + "    webApiServer=\"evsweb1226.idrive.com\" webApiServerIP=\"66.51.30.113\"/>";

    final XmlMapper xmlMapper = new XmlMapper();

    final GetServerAddressResponse result = xmlMapper
        .readValue(responseXml.getBytes(), GetServerAddressResponse.class);

    assertEquals("SUCCESS", result.getMessage());
    assertEquals("evs1226.idrive.com", result.getCmdUtilityServer());
    assertEquals("66.51.30.112", result.getCmdUtilityServerIP());
    assertEquals("evsweb1226.idrive.com", result.getWebApiServer());
    assertEquals("66.51.30.113", result.getWebApiServerIP());
  }
}