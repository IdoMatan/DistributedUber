package zkwatchers;

import api.ZkService;
import lombok.Setter;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;

@Setter
public class MasterChangeListener implements IZkChildListener {

    private ZkService zkService;

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
            try {
//        zkService.electForMaster(parentPath);
                zkService.singleReElect(parentPath);
            } catch (ZkNodeExistsException e) {
//        log.info("master already created");
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
