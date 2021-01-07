public class Main {
    public static void main(String[] args) throws Exception{


        Receiver server = new Receiver(8980);
        server.start();
        System.out.println("Server started");

        server.blockUntilShutdown();


    }
}
