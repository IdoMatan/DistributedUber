package api;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;

import java.util.List;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
public interface ZkService {

    String getLeaderNodeData(String shard);

    void electForMaster(String shard);

    boolean masterExists(String shard);

    void addToLiveNodes(String nodeName, String data);

    List<String> getLiveNodes();

    void addToAllNodes(String nodeName, String data);

    List<String> getAllNodes();

    void deleteNodeFromCluster(String node);

    void createAllParentNodes();
    void createCityNode(String shard, String city_name);

    String getLeaderNodeData2();

    String getZNodeData(String path);

    void createNodeInElectionZnode(String data);

    void registerChildrenChangeWatcher(String path, IZkChildListener iZkChildListener);

    void registerZkSessionStateListener(IZkStateListener iZkStateListener);

    List<String> getCities();
    List<String> getShardNodes(String shard);
}
