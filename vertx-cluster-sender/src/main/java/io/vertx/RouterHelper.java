package io.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.web.Router;

import static io.vertx.Constants.CONTENT_TYPE;
import static io.vertx.Constants.HTML_PRODUCE;


/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class RouterHelper
{
    private RouterHelper(){}

    /**
     *
     * @param vertx
     * @param welcomeMessage
     * @return
     */
    public static Router createRouter(final Vertx vertx, final String welcomeMessage){
        final Router router = Router.router(vertx);

        HealthCheckHandler healthCheckHandler1 = HealthCheckHandler.create(vertx);

        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader(CONTENT_TYPE, HTML_PRODUCE).end("<h1>" + welcomeMessage + "</h1>");
        });

        router.get("/health*").handler(healthCheckHandler1);

        // for lomger checks
//        healthCheckHandler1.register("my-procedure-name", promise -> {
//            // Do the check ....
//            // Upon success do
//            promise.complete(Status.OK());
//            // In case of failure do:
//            promise.complete(Status.KO());
//        });

        return router;
    }
}