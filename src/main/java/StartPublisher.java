
import com.adscoop.publisher.config.Aws;
import com.adscoop.publisher.config.Config;
import com.adscoop.publisher.handlers.BannerHandler;
import com.adscoop.publisher.handlers.CreateBannerHandler;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;

import com.adscoop.publisher.handlers.CorsHandler;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

import java.net.URI;
import java.nio.file.Paths;

/**
 * Created by thokle on 05/09/2016.
 */
public class StartPublisher {

    public static void main(String... args) throws Exception {

        RxRatpack.initialize();
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.registry(Guice.registry(bindingsSpec ->
                bindingsSpec.module(Config.class).module(ServiceCommonConfig.class)))
                .serverConfig(serverConfigBuilder -> serverConfigBuilder.port(9999).publicAddress(new URI("adscoop")).yaml(StartPublisher.class.getResource("/ratpack/resources.yml")).require("/aws",Aws.class))
                .handlers(chain -> chain.all(CorsHandler.class).get("banner/:name", BannerHandler.class).post("/create", CreateBannerHandler.class)));

    }

}

