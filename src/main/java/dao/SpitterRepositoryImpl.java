package dao;

import model.Spitter;
import org.springframework.stereotype.Component;

/**
 * Created by yh on 17/11/24.
 */
@Component
public class SpitterRepositoryImpl implements SpitterRepository {
    public Spitter save(Spitter spitter) {
        return spitter;
    }

    public Spitter findByUserName(String userName) {
        return new Spitter(userName, userName, userName, userName);
    }
}
