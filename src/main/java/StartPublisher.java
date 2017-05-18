import com.adscoop.publisher.config.ConfigModule;
import com.adscoop.publisher.handlers.CorsHandler;
import com.adscoop.publisher.jobs.BannerPusherHandler;
import com.adscoop.publisher.modules.Config;
import com.adscoop.publisher.modules.ServiceCommonConfigModule;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

/**
 * Created by thokle on 05/09/2016.
 */
public class StartPublisher {

    public static void main(String... args) throws Exception {

        RxRatpack.initialize();
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(serverConfigBuilder ->
                serverConfigBuilder.baseDir(BaseDir.find()).yaml("ratpack.yaml").require("/db", Config.class).props("ratpack.properties").env().sysProps().build()).registry(Guice.registry(bindingsSpec ->

                bindingsSpec.module(ConfigModule.class).module(ServiceCommonConfigModule.class))).handlers(chain -> chain.all(CorsHandler.class).get("banner/:token", BannerPusherHandler.class)));
    }

}
