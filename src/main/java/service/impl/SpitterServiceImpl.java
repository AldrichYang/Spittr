package service.impl;

import org.springframework.stereotype.Service;
import service.SpitterService;

/**
 * Created by yh on 2019-01-30.
 */
@Service
public class SpitterServiceImpl implements SpitterService {
    @Override
    public String helloSpitter() {
        return "Hello Spitter Web App";
    }
}
