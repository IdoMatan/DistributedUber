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

//    @Value(("${server.port}"))
//    public static String server_port;
//
//    public static String getHostPostOfServer() {
//        String ip;
//        try {
//            ip = InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            throw new RuntimeException("failed to fetch Ip!", e);
//        }
////        String port = server_port;
//        int port = Integer.parseInt(System.getProperty("server.port"));
////        ipPort = ip.concat(":").concat(String.valueOf(port));
//        return ip.concat(":").concat(server_port);
//    }
    private static String ipPort = null;

    public static String getHostPostOfServer() {
        if (ipPort != null) {
            return ipPort;
        }
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to fetch Ip!", e);
        }
        int port = Integer.parseInt(System.getProperty("server.port"));
        ipPort = ip.concat(":").concat(String.valueOf(port));
        return ipPort;
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private ZkConfig() {}
}
