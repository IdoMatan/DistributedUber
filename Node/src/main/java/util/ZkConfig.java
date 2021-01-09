package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
public final class ZkConfig {

    public static final String ELECTION_MASTER = "/election/master";
    public static final String ELECTION_NODE = "/election";
    public static final String ELECTION_NODE_2 = "/election2";
    public static final String LIVE_NODES = "/liveNodes";
    public static final String ALL_NODES = "/allNodes";
    public static final String CITIES = "/serving_cities";
    public static final String SHARD_DIR = "/SHARDS";
    public static final String[] SHARDS = new String[] {"/A","/B","/C","/D"} ;

    private static String ipPortREST = null;
    private static String ipPortgRPC = null;

    public static String getHostPostOfRESTServer() {
        if (ipPortREST != null) {
            return ipPortREST;
        }
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to fetch Ip!", e);
        }
        int port = Integer.parseInt(System.getProperty("rest.port"));
        ipPortREST = ip.concat(":").concat(String.valueOf(port));
        return ipPortREST;
    }

    public static String getHostPostOfgRPCServer() {
        if (ipPortgRPC != null) {
            return ipPortgRPC;
        }
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to fetch Ip!", e);
        }
        int port = Integer.parseInt(System.getProperty("grpc.port"));
        ipPortgRPC = ip.concat(":").concat(String.valueOf(port));
        return ipPortgRPC;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private ZkConfig() {}
}
