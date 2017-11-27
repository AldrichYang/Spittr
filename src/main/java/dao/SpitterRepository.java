package dao;

import model.Spitter;

/**
 * Created by yh on 17/11/24.
 */
public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUserName(String userName);
}
