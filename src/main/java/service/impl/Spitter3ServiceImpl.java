package service.impl;

import org.springframework.stereotype.Service;
import service.Spitter3Service;

/**
 * Created by yh on 2019-02-28.
 */
@Service
public class Spitter3ServiceImpl implements Spitter3Service {
    @Override
    public String hello3Spitter() {
        return "Hello 2 Spitter Web App";
    }
}
