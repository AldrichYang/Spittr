package controller.rest;

import dao.SpittleRepository;
import infra.exception.BizError;
import infra.exception.SpittleNotFoundException;
import model.Spittle;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Created by yh on 2019-03-05.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleControllerRest1 {

    private SpittleRepository spittleRepository;

    public SpittleControllerRest1(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(path = "/spittles", method = RequestMethod.GET)
    public List<Spittle> spittles1() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Spittle saveSpittle(@RequestBody Spittle spittle) {
        return spittleRepository.save(spittle);
    }

    /**
     * 通过ResponseEntity返回头部信息和状态码
     *
     * @param spittle
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle1(Spittle spittle) {
        Spittle spittle1 = spittleRepository.save(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = URI.create("http://localhost:8080/spittr/spittles/" + spittle.getId());
        headers.setLocation(locationUri);
        return new ResponseEntity<>(spittle1, headers, HttpStatus.CREATED);
    }

    /**
     * 使用UriComponentsBuilder来构建Location URI
     * 通过ResponseEntity返回头部信息和状态码
     *
     * @param spittle
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle2(Spittle spittle, UriComponentsBuilder builder) {
        Spittle spittle1 = spittleRepository.save(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = builder.path("/spittles/").path(String.valueOf(spittle1.getId())).build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<>(spittle1, headers, HttpStatus.CREATED);
    }

    /**
     * 无错误处理的实现
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Spittle spittleById(@PathVariable long id) {
        return spittleRepository.findOne(id);
    }

    /**
     * 如果业务数据不存在，返回不同的状态码
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById1(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        HttpStatus status = null == spittle ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(spittle, status);
    }

    /**
     * 如果业务数据不存在，返回不同的状态码和错误信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> spittleById2(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (Objects.isNull(spittle)) {
            BizError error = new BizError(4, "Spittle[" + id + "] not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spittle, HttpStatus.OK);
    }

    /**
     * 使用异常处理器来拆分正常逻辑和错误逻辑
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById3(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (Objects.isNull(spittle)) {
            throw new SpittleNotFoundException(id);
        }
        return new ResponseEntity<>(spittle, HttpStatus.OK);
    }

    /**
     * 使用异常处理器来拆分正常逻辑和错误逻辑。
     * 使用ResponseBody注解简化返回类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Spittle spittleById4(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (Objects.isNull(spittle)) {
            throw new SpittleNotFoundException(id);
        }
        return spittle;
    }
}
