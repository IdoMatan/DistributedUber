package zkwatchers;

import api.ZkService;
import generated.SyncParam;
import host.controllers.Sender;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Setter;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.DepartureRepository;

import java.util.List;

@Setter
public class MasterChangeListener implements IZkChildListener {

    private ZkService zkService;

    @Autowired
    DepartureRepository departureRepository;

    /**
     * listens for creation/deletion of znode "master" under /election znode and updates the
     * clusterinfo
     *
     * @param parentPath
     * @param currentChildren
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChildren) {
        if (currentChildren.isEmpty()) {
            boolean reElected = false;
            try {
//        zkService.electForMaster(parentPath);
                zkService.singleReElect(parentPath);
                reElected = true;
            } catch (ZkNodeExistsException e) {
//        log.info("master already created");
            }
            if (reElected) {

                String[] parentPathList = parentPath.split("/");  // parentPath = SHARD_DIR + shard + "/" + c + ELECTION_NODE
                String shard = parentPathList[2];
                String city = parentPathList[3];

                List<String> followers = zkService.getFollowers(shard);
                var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
                int bestSyncParamInt = departureRepository.getSize(city);

                if (bestSyncParamInt != Integer.parseInt(zkService.getLiveRidesSync(shard, city))) {
                    String bestTarget = null;
                    for (String target : followers) {
                        if (!myFullURI.equals(target)) {
                            String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                            ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                            Sender client = new Sender(channel);
                            SyncParam syncParamProto = client.getSyncParam(city);
                            int syncParamInt = Integer.parseInt(syncParamProto.getSyncParamProto());

                            if (syncParamInt > bestSyncParamInt) {
                                bestSyncParamInt = syncParamInt;
                                bestTarget = target;
                            }
                        }
                    }
                    if (bestTarget != null) {
                        zkService.updateMaster(parentPath, bestTarget);
                    }
                }
            }
        }
//    } else {
////      String leaderNode = zkService.getLeaderNodeData("A");
//    }
    }

    public void setZkService(ZkService zkService) {
        this.zkService = zkService;
    }
}
