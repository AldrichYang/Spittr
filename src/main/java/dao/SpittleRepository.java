package dao;

import model.Spittle;

import java.util.List;

/**
 * Created by yh on 17/11/22.
 */
public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

}
