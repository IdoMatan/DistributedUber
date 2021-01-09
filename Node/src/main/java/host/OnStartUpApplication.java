package host;

import api.ZkService;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import repository.CityRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import static util.ZkConfig.getHostPostOfRESTServer;

/**
 * @author "IdoGlanzMatanWeks" 01/01/21
 */
//@Component
//public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
@Component
public class OnStartUpApplication implements ApplicationListener<ContextRefreshedEvent> {

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ZkService zkService;

    @Autowired
    private CityRepository citiesRepository = new CityRepository();

    @Autowired
    private IZkChildListener allNodesChangeListener;

    @Autowired
    private IZkChildListener liveNodeChangeListener;

    @Autowired
    private IZkChildListener masterChangeListener;

    @Autowired
    private IZkStateListener connectStateChangeListener;

    private ApplicationArguments appArgs;

    @Value("${shard}")
    private String myShard;

    @Value(("${server.port}"))
    public String restPort;

    @Value(("${grpc.port}"))
    public String grpcPort;

    public OnStartUpApplication(ApplicationArguments appArgs) {
        this.appArgs = appArgs;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        City myCity = citiesRepository.getCity(myCityName);
        System.setProperty("rest.port", restPort);
        System.setProperty("grpc.port", grpcPort);

        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            System.setProperty("server.ip", ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to fetch Ip!", e);
        }
        System.setProperty("myIP", ip);

        try {
            System.out.println("App Args: " + Arrays.asList(appArgs.getSourceArgs()));

            // create ZK tree structure
            zkService.createZkTree(myShard);
            // add city layer of Znodes
            zkService.createCityNodes(myShard, citiesRepository.getShardCities(myShard));
            // iterate over cities in shard and see if any need a new leader (and if so propose myself)
            zkService.electForMaster(myShard);

            // sync data with master
//            syncDataFromMaster(zkService.getLeaderNodeGRPChost(myShard));

            // register watchers for leader change
            zkService.registerChildrenChangeWatcher(myShard, masterChangeListener);

        } catch (Exception e) {
            throw new RuntimeException("Startup failed!", e);
        }
    }

    private void syncDataFromMaster(String leader) {
        if (getHostPostOfRESTServer().equals(leader)) {  // I am the master, no need to sync!
            return;
        }
        // todo ADD HERE gRPC request for list of open rides for relevant city!
    }
}
