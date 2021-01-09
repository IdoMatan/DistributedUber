package host;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

import java.io.IOException;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/** @author "IdoGlanzMatanWeks" 01/01/21 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class,
        RepositoryRestMvcAutoConfiguration.class})
//@EnableAutoConfiguration()
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {

    @Value(("${server.port}"))
    public String restPort;

    @Value(("${grpc.port}"))
    public String grpcPort;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 5){
            System.out.println("Please supply city to serve: A/B/C/D/E/F");
            System.exit(1);
        }
        else {
            SpringApplication.run(Application.class, args);

            Receiver server = new Receiver(Integer.parseInt(System.getProperty("grpc.port")));
            server.start();
            System.out.println("Server started");
            server.blockUntilShutdown();

        }
    }
}