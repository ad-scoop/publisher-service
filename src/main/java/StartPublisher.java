import com.adscoop.publisher.actions.SchedulerAction;
import com.adscoop.publisher.config.ConfigModule;
import com.adscoop.publisher.handlers.BannerPusherHandler;
import com.adscoop.publisher.handlers.CorsHandler;
import com.adscoop.publisher.handlers.HandlerByToken;
import com.adscoop.publisher.handlers.TimerHandler;
import com.adscoop.publisher.modules.Config;
import com.adscoop.publisher.modules.ServiceCommonConfigModule;
import com.adscoop.publisher.quartz.QuartzJob;
import com.adscoop.publisher.services.SchedulerTimerService;
import org.neo4j.ogm.session.Session;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.util.Types;

/**
 * Created by thokle on 05/09/2016.
 */
public class StartPublisher {

    public static void main(String... args) throws Exception {


        RxRatpack.initialize();
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(serverConfigBuilder ->
                serverConfigBuilder.baseDir(BaseDir.find()).yaml("ratpack.yaml").require("/db", Config.class)
                        .props("ratpack.properties").development(true).env().sysProps().build()).registry(Guice.registry(bindingsSpec ->
                bindingsSpec.module(ConfigModule.class)
                        .module(ServiceCommonConfigModule.class).bind(SchedulerTimerService.class)
                        )).
                handlers(chain -> chain.prefix("demo", chain1 -> chain1.all(CorsHandler.class)
                        .get("try/:name", BannerPusherHandler.class).get("timer/:name/:sec", TimerHandler.class).get("token/:token", HandlerByToken.class))));

    }
}
