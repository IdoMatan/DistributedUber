package host;

import api.ZkService;
import model.City;
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

import java.util.Arrays;

import static util.ZkConfig.getHostPostOfServer;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
//@Component
//public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
@Component
public class OnStartUpApplication implements ApplicationListener<ContextRefreshedEvent> {

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired private ZkService zkService;

    @Autowired private CityRepository citiesRepository = new CityRepository();

    @Autowired private IZkChildListener allNodesChangeListener;

    @Autowired private IZkChildListener liveNodeChangeListener;

    @Autowired private IZkChildListener masterChangeListener;

    @Autowired private IZkStateListener connectStateChangeListener;

    private ApplicationArguments appArgs;

    @Value("${my_city}")
    public static String myCityName;

    private City myCity = citiesRepository.getCity(myCityName);

    public OnStartUpApplication(ApplicationArguments appArgs) {
        this.appArgs = appArgs;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            // Create my_city
            System.out.println("App Args: " + Arrays.asList(appArgs.getSourceArgs()));
            // todo extract my city

            // create all parent nodes /cities /election, /all_nodes, /live_nodes
            zkService.createAllParentNodes();
            // add EPHEMERAL city node
            zkService.createCityNode(myCity.shard, myCity.name);

            // add this server to cluster by creating znode under /all_nodes, with name as "host:port"
            zkService.addToAllNodes(getHostPostOfServer(), "cluster node");

            // add client to election list to see if a leader needs to be elected
            if (!zkService.masterExists(myCity.shard)) {
                zkService.electForMaster(myCity.shard);
            }
//            else {
//                ClusterInfo.getClusterInfo().setMaster(zkService.getLeaderNodeData());
//            }

            // sync person data from master
            // sync data with master
            syncDataFromMaster(zkService.getLeaderNodeData(myCity.shard));

            // register watchers for leader change
//            zkService.registerChildrenChangeWatcher(my_city.getShard().concat(ELECTION_NODE), masterChangeListener);
//            zkService.registerZkSessionStateListener(connectStateChangeListener);
        } catch (Exception e) {
            throw new RuntimeException("Startup failed!", e);
        }
    }

    private void syncDataFromMaster(String leader) {
        if (getHostPostOfServer().equals(leader)) {  // I am the master, no need to sync!
            return;
        }
        String requestUrl;
        // todo ADD HERE gRPC request for list of open rides for relevant city!

//        requestUrl = "http://".concat(leader.concat("/persons"));
//        List<Person> persons = restTemplate.getForObject(requestUrl, List.class);
//        DataStorage.getPersonListFromStorage().addAll(persons);
    }
}
