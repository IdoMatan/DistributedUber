package impl;

import api.ZkService;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import util.StringSerializer;

import java.util.Collections;
import java.util.List;

import static util.ZkConfig.*;

//import lombok.extern.slf4j.Slf4j;

/**
 * @author "Ido Glanz, Matan Weks" 01/01/21
 */
//@Slf4j
public class ZkServiceImpl implements ZkService {

    private ZkClient zkClient;

//    private static final Logger log = Logger.getLogger("zooLog");

    public ZkServiceImpl(String hostPort) {
        zkClient = new ZkClient(hostPort, 1200000000, 3000, new StringSerializer());
    }

    public void closeConnection() {
        zkClient.close();
    }

    @Override
    public String getLeaderNodeGRPChost(String shard, String city) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }

        // get host of REST (the data of the master node)
        String leaderRESThost = zkClient.readData(SHARD_DIR.concat(shard).concat("/").concat(city).concat(ELECTION_MASTER), true);
        // get host of gRPC (the data of the server node in live_nodes (i.e. node name is RESThost, data is gRPC IP)
        return zkClient.readData(SHARD_DIR.concat(shard).concat(LIVE_NODES).concat("/").concat(leaderRESThost), true);
    }

    @Override
    public String getLeaderNodeRESThost(String shard, String city) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        return zkClient.readData(SHARD_DIR.concat(shard).concat("/").concat(city).concat(ELECTION_MASTER), true);
    }

    @Override
    public void electForMaster(String shard) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        List<String> cities = zkClient.getChildren(SHARD_DIR + shard);
        for (String c : cities) {
            if (c.startsWith("city")) {
                if (!zkClient.exists(SHARD_DIR.concat(shard).concat("/").concat(c).concat(ELECTION_MASTER))) {
                    // a master doesn't exist --> propose myself
                    try {
                        zkClient.create(
                                SHARD_DIR.concat(shard).concat("/").concat(c).concat(ELECTION_MASTER),
                                getHostPostOfRESTServer(),
                                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                                CreateMode.EPHEMERAL);
                    } catch (ZkNodeExistsException e) {
                        System.out.println("Master already elected! ".concat(e.toString()));
                    }
                }
            }
        }
    }

    @Override
    public void singleReElect(String parentPath) {
        if (!zkClient.exists(parentPath.concat("/master"))) {
            // a master doesn't exist --> propose myself
            try {
                zkClient.create(
                        parentPath.concat("/master"),
                        getHostPostOfRESTServer(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);
            } catch (ZkNodeExistsException e) {
                System.out.println("Master already elected! ".concat(e.toString()));
            }
        }
    }

    @Override
    public boolean masterExists(String shard) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        List<String> cities = zkClient.getChildren(SHARD_DIR + shard);
        for (String c : cities) {
            if (zkClient.exists(SHARD_DIR.concat(shard).concat("/").concat(c).concat(ELECTION_MASTER))) {
                System.out.println("I shouldn't be here....");

            }
        }
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
    public void createZkTree(String shard) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }

        if (!zkClient.exists(SHARD_DIR)) {
            zkClient.create(SHARD_DIR, "all shards are listed here", CreateMode.PERSISTENT);
        }
        for (String shard_name : SHARDS) {
            if (!zkClient.exists(SHARD_DIR.concat(shard_name))) {
                zkClient.create(SHARD_DIR.concat(shard_name), "in charge of cities [A,B,C..]", CreateMode.PERSISTENT);
            }
        }
        if (!zkClient.exists(SHARD_DIR.concat(shard).concat(LIVE_NODES))) {
            zkClient.create(SHARD_DIR.concat(shard).concat(LIVE_NODES), "List of active servers", CreateMode.PERSISTENT);
        }
        zkClient.create(SHARD_DIR.concat(shard).concat(LIVE_NODES).concat("/").concat(getHostPostOfRESTServer()), getHostPostOfgRPCServer(), CreateMode.EPHEMERAL);

        if (!zkClient.exists(CITIES)) {
            zkClient.create(CITIES, "live list of cities being served", CreateMode.PERSISTENT);
        }
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
    }

    @Override
    public void createCityNodes(String shard, List<String> cities) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        for (String c : cities) {
            var city = SHARD_DIR.concat(shard).concat("/").concat(c);
            if (!zkClient.exists(city)) {
                zkClient.create(city, c.concat("_node"), CreateMode.PERSISTENT);
            }
            if (!zkClient.exists(city.concat(ELECTION_NODE))) {
                zkClient.create(city.concat(ELECTION_NODE), "election_node", CreateMode.PERSISTENT);
            }
            if (!zkClient.exists(city.concat(LIVE_RIDES))) {
                zkClient.create(city.concat(LIVE_RIDES), "0", CreateMode.PERSISTENT);
            }
        }
    }

    @Override
    public void updateLiveRidesSync(String shard, String city, String nRides) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        try {
            zkClient.writeData(SHARD_DIR.concat(shard).concat("/").concat(city).concat(LIVE_RIDES), nRides);
        } catch (ZkNodeExistsException e) {
            System.out.println("I'm not sure why ".concat(e.toString()));

        }
    }

    @Override
    public String getLiveRidesSync(String shard, String city) {
        String nRides = "NA";
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        try {
            nRides = zkClient.readData(SHARD_DIR.concat(shard).concat("/").concat(city).concat(LIVE_RIDES));
        } catch (ZkNodeExistsException e) {
            System.out.println("I'm not sure why ".concat(e.toString()));
        }
        return nRides;
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
    public void registerChildrenChangeWatcher(String shard, IZkChildListener iZkChildListener) {

        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        List<String> cities = zkClient.getChildren(SHARD_DIR + shard);
        for (String c : cities) {
            if (c.startsWith("city")) {
                zkClient.subscribeChildChanges(SHARD_DIR + shard + "/" + c + ELECTION_NODE, iZkChildListener);
            }
        }
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
    public List<String> getFollowers(String shard) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        return zkClient.getChildren(SHARD_DIR + shard + LIVE_NODES);
    }

    @Override
    public List<String> getShardNodes(String shard) {
        if (!shard.startsWith("/")) {
            shard = "/".concat(shard);
        }
        return zkClient.getChildren(SHARD_DIR.concat(shard));
    }

}
