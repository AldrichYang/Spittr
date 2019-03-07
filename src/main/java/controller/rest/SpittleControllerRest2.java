package controller.rest;

import dao.SpittleRepository;
import infra.exception.SpittleNotFoundException;
import model.Spittle;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by yh on 2019-03-05.
 */
@RestController
@RequestMapping("/spittles")
public class SpittleControllerRest2 {

    private SpittleRepository spittleRepository;

    public SpittleControllerRest2(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(path = "/spittles", method = RequestMethod.GET)
    public List<Spittle> spittles1() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Spittle saveSpittle(Spittle spittle) {
        return spittleRepository.save(spittle);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Spittle spittleById5(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (Objects.isNull(spittle)) {
            throw new SpittleNotFoundException(id);
        }
        return spittle;
    }


}
