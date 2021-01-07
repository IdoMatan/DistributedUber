package impl;

import api.ZkService;
//import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.util.ResourceUtils;
import util.StringSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static util.ZkConfig.*;

/** @author "Ido Glanz, Matan Weks" 01/01/21 */
//@Slf4j
public class ZkServiceImpl implements ZkService {
    File file;
    {
        try {
            file = ResourceUtils.getFile("resources/json/CitiesMap");
            Object temp = JSONValue.parse(file.toString());
            JSONObject CitiesMap = (JSONObject) temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            file = ResourceUtils.getFile("resources/json/CitiesMap");
            Object temp = JSONValue.parse(file.toString());
            JSONObject CitiesMap = (JSONObject) temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private ZkClient zkClient;
//    private static final Logger log = Logger.getLogger("zooLog");

    public ZkServiceImpl(String hostPort) {
        zkClient = new ZkClient(hostPort, 12000, 3000, new StringSerializer());
    }

    public void closeConnection() {
        zkClient.close();
    }

    @Override
    public String getLeaderNodeData(String shard) {

        return zkClient.readData(SHARD_DIR.concat(shard).concat(ELECTION_MASTER), true);
    }

    @Override
    public void electForMaster(String shard) {
        if (!zkClient.exists(SHARD_DIR.concat(shard).concat(ELECTION_NODE))) {
            zkClient.create(SHARD_DIR.concat(shard).concat(ELECTION_NODE), "election_node", CreateMode.PERSISTENT);
        }
        try {
            zkClient.create(
                    SHARD_DIR.concat(shard).concat(ELECTION_MASTER),
                    getHostPostOfServer(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);
        } catch (ZkNodeExistsException e) {
            System.out.println("Master already elected! ".concat(e.toString()));
//            log.info("Master already elected! ".concat(e.toString()));
        }
    }

    @Override
    public boolean masterExists(String shard) {
        return zkClient.exists(SHARD_DIR.concat(shard).concat(ELECTION_MASTER));
    }

    @Override
    public void addToLiveNodes(String nodeName, String data) {
        if (!zkClient.exists(LIVE_NODES)) {
            zkClient.create(LIVE_NODES, "all live nodes are displayed here", CreateMode.PERSISTENT);
        }
        String childNode = LIVE_NODES.concat("/").concat(nodeName);
        if (zkClient.exists(childNode)) {
            return;
        }
        zkClient.create(childNode, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Override
    public List<String> getLiveNodes() {
        if (!zkClient.exists(LIVE_NODES)) {
            throw new RuntimeException("No node /liveNodes exists");
        }
        return zkClient.getChildren(LIVE_NODES);
    }

    @Override
    public void addToAllNodes(String nodeName, String data) {
        if (!zkClient.exists(ALL_NODES)) {
            zkClient.create(ALL_NODES, "all live nodes are displayed here", CreateMode.PERSISTENT);
        }
        String childNode = ALL_NODES.concat("/").concat(nodeName);
        if (zkClient.exists(childNode)) {
            return;
        }
        zkClient.create(childNode, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Override
    public List<String> getAllNodes() {
        if (!zkClient.exists(ALL_NODES)) {
            throw new RuntimeException("No node /allNodes exists");
        }
        return zkClient.getChildren(ALL_NODES);
    }

    @Override
    public void deleteNodeFromCluster(String node) {
        zkClient.delete(ALL_NODES.concat("/").concat(node));
        zkClient.delete(LIVE_NODES.concat("/").concat(node));
    }

    @Override
    public void createAllParentNodes() {
        if (!zkClient.exists("/SHARDS")) {
            zkClient.create("/SHARDS", "all shards are listed here", CreateMode.PERSISTENT);
            for (String shard : SHARDS) {
                zkClient.create("/SHARDS".concat(shard), "in charge of cities [A,B,C..]", CreateMode.PERSISTENT);
            }
        }
        if (!zkClient.exists(CITIES)) {
            zkClient.create(CITIES, "live list of cities being served", CreateMode.PERSISTENT);
        }
//        if (!zkClient.exists(ALL_NODES)) {
//            zkClient.create(ALL_NODES, "all live nodes are displayed here", CreateMode.PERSISTENT);
//        }
//        if (!zkClient.exists(LIVE_NODES)) {
//            zkClient.create(LIVE_NODES, "all live nodes are displayed here", CreateMode.PERSISTENT);
//        }
//        if (!zkClient.exists(ELECTION_NODE)) {
//            zkClient.create(ELECTION_NODE, "election node", CreateMode.PERSISTENT);
//        }
    }

    @Override
    public void createCityNode(String shard, String city_name){
        var city = SHARD_DIR.concat(shard).concat("/").concat(city_name);
        if (!zkClient.exists(city)) {
            zkClient.create(city, "city_node_".concat(city_name), CreateMode.PERSISTENT);

        }
        String my_node = city.concat("/").concat((String)getHostPostOfServer());
        // Check if city already shows up in the cities being served list
        if (!zkClient.exists(CITIES.concat("/").concat(city_name)))
            zkClient.create(CITIES.concat("/").concat(city_name),"SHARD".concat(shard), CreateMode.EPHEMERAL);

        // check if my my node is by any chance already in shard active servers
        if (zkClient.exists(my_node)) {
            return;
        }
        zkClient.create(my_node,"localhost:".concat(getHostPostOfServer()), CreateMode.EPHEMERAL);
    }

    @Override
    public String getLeaderNodeData2() {
        if (!zkClient.exists(ELECTION_NODE_2)) {
            throw new RuntimeException("No node /election2 exists");
        }
        List<String> nodesInElection = zkClient.getChildren(ELECTION_NODE_2);
        Collections.sort(nodesInElection);
        String masterZNode = nodesInElection.get(0);
        return getZNodeData(ELECTION_NODE_2.concat("/").concat(masterZNode));
    }

    @Override
    public String getZNodeData(String path) {
        return zkClient.readData(path, null);
    }

    @Override
    public void createNodeInElectionZnode(String data) {
        if (!zkClient.exists(ELECTION_NODE_2)) {
            zkClient.create(ELECTION_NODE_2, "election node", CreateMode.PERSISTENT);
        }
        zkClient.create(ELECTION_NODE_2.concat("/node"), data, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    @Override
    public void registerChildrenChangeWatcher(String path, IZkChildListener iZkChildListener) {
        zkClient.subscribeChildChanges(path, iZkChildListener);
    }

    @Override
    public void registerZkSessionStateListener(IZkStateListener iZkStateListener) {
        zkClient.subscribeStateChanges(iZkStateListener);
    }
    @Override
    public List<String> getCities() {
        return zkClient.getChildren(CITIES);
    }

    @Override
    public List<String> getShardNodes(String shard){
        return zkClient.getChildren(SHARD_DIR.concat(shard));
    }

}
