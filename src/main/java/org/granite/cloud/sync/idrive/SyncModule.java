package org.granite.cloud.sync.idrive;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.granite.configuration.ApplicationConfiguration;
import org.granite.configuration.ConfigTools;

@Module(
    injects = SyncMain.class
)
public class SyncModule {

  @Provides
  @Singleton
  ApplicationConfiguration provideApplicationConfiguration() {
    return ConfigTools.readConfiguration(
        "idrive-sync.properties"
        , "local-idrive-sync.properties"
        , "/etc/idrive-sync/idrive-sync.properties"
    );
  }

}
