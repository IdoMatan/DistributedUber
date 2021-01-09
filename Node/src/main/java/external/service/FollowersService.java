package external.service;

import api.ZkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowersService {
    @Autowired
    private ZkService zkService;
}
