package host.configuration;

import api.ZkService;
import external.service.PathPlaningService;
import external.service.PdCitiesService;
import host.LocalRideDistributionService;
import impl.ZkServiceImpl;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import repository.CityRepository;
import repository.DepartureRepository;
import repository.LiveMapRepository;
import repository.PassengersRepository;
import zkwatchers.AllNodesChangeListener;
import zkwatchers.ConnectStateChangeListener;
import zkwatchers.LiveNodeChangeListener;
import zkwatchers.MasterChangeListener;

@Configuration
public class BeanConfig {

  @Bean(name = "cityRepository")
  @Scope("singleton")
  public CityRepository citiesRepository() { return new CityRepository(); }

  @Bean(name = "liveMapRepository")
  @Scope("singleton")
  public LiveMapRepository liveMapRepository() { return new LiveMapRepository(); }

  @Bean(name = "passengersRepository")
  @Scope("singleton")
  public PassengersRepository passengersRepository() { return new PassengersRepository(); }

  @Bean(name = "departureRepository")
  @Scope("singleton")
  public DepartureRepository departureRepository() { return new DepartureRepository(); }

  @Bean(name = "localRideDistribution")
  @Scope("singleton")
  public LocalRideDistributionService localRideDistribution() { return new LocalRideDistributionService(); }

  @Bean(name = "pdCitiesService")
  @Scope("singleton")
  public PdCitiesService pdCities() {
    return new PdCitiesService();
  }

  @Bean(name = "zkService")
  @Scope("singleton")
  public ZkService zkService() {
    String zkHostPort = "localhost:2181"; //System.getProperty("zk.url");
    return new ZkServiceImpl(zkHostPort);
  }
//
  @Bean(name = "allNodesChangeListener")
  @Scope("singleton")
  public IZkChildListener allNodesChangeListener() {
    return new AllNodesChangeListener();
  }

  @Bean(name = "liveNodeChangeListener")
  @Scope("singleton")
  public IZkChildListener liveNodeChangeListener() {
    return new LiveNodeChangeListener();
  }

  @Bean(name = "masterChangeListener")
//  @ConditionalOnProperty(name = "leader.algo", havingValue = "1")
  @Scope("singleton")
  public IZkChildListener masterChangeListener() {
    MasterChangeListener masterChangeListener = new MasterChangeListener();
    masterChangeListener.setZkService(zkService());
    return masterChangeListener;
  }

  @Bean(name = "pathPlanning")
  @Scope("singleton")
  public PathPlaningService pathPlanning() {
    return new PathPlaningService();
  }

//  @Bean(name = "masterChangeListener")
//  @ConditionalOnProperty(name = "leader.algo", havingValue = "2", matchIfMissing = true)
//  @Scope("singleton")
//  public IZkChildListener masterChangeListener2() {
//    MasterChangeListenerApproach2 masterChangeListener = new MasterChangeListenerApproach2();
//    masterChangeListener.setZkService(zkService());
//    return masterChangeListener;
//  }

  @Bean(name = "connectStateChangeListener")
  @Scope("singleton")
  public IZkStateListener connectStateChangeListener() {
    ConnectStateChangeListener connectStateChangeListener = new ConnectStateChangeListener();
    connectStateChangeListener.setZkService(zkService());
    return connectStateChangeListener;
  }
}
