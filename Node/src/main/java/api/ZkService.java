package api;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;

import java.util.List;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
public interface ZkService {

    String getLeaderNodeGRPChost(String shard, String city);

    String getLeaderNodeRESThost(String shard, String city);


    void electForMaster(String shard);
    public void singleReElect(String parentPath);

    boolean masterExists(String shard);

    void addToLiveNodes(String nodeName, String data);

    List<String> getLiveNodes();

    void addToAllNodes(String nodeName, String data);

    List<String> getAllNodes();

    void deleteNodeFromCluster(String node);

    void createAllParentNodes();

    void createZkTree(String shard);

    void createCityNodes(String shard, List<String> cities);

    String getLeaderNodeData2();

    String getZNodeData(String path);

    void createNodeInElectionZnode(String data);

    void registerChildrenChangeWatcher(String shard, IZkChildListener iZkChildListener);

    void registerZkSessionStateListener(IZkStateListener iZkStateListener);

    void updateLiveRidesSync(String shard, String city, String nRides);

    String getLiveRidesSync(String shard, String city);

    List<String> getCities();

    List<String> getFollowers(String shard);

    List<String> getShardNodes(String shard);
}
