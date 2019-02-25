package service.impl;

import org.springframework.stereotype.Service;
import service.Spitter2Service;

/**
 * Created by yh on 2019-01-30.
 */
@Service
public class Spitter2ServiceImpl implements Spitter2Service {
    @Override
    public String hello2Spitter() {
        return "Hello 2 Spitter Web App";
    }
}
