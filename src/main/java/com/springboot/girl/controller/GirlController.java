package com.springboot.girl.controller;

import com.springboot.girl.domain.Girl;
import com.springboot.girl.repository.GirlRepository;
import com.springboot.girl.domain.Result;
import com.springboot.girl.service.GirlService;
import com.springboot.girl.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @ApiOperation(value="查询所有女生", notes="")
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("获取女生列表成功");
        return girlRepository.findAll();
    }

    @ApiOperation(value="通过编号查询一个女生", notes="根据url的id来查询一个女生信息")
    //@ApiImplicitParam(name = "id", value = "女生id", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        Girl girl = girlRepository.findById(id).orElse(null);
        return girl;
    }

    @ApiOperation(value="通过年龄查询女生列表", notes="根据url的id来查询一个女生信息")
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> GirlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

/*    @ApiOperation(value = "增加一个女生", notes = "")
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age,
                        @RequestParam("money") Long money) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        girl.setMoney(money);
        girlRepository.save(girl);
        Result<Girl> girlResult = new Result<Girl>();
        girlResult.setCode(0);
        girlResult.setMessage("成功");
        girlResult.setData(girl);
        return girlResult;
    }*/

    @ApiOperation(value = "增加一个女生", notes = "")
    @PostMapping(value = "/girls/limit")
    public Result<Girl> girlAdd(@Valid @ModelAttribute Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }

    @ApiOperation(value = "增加两个女生", notes = "在service层启用事物注解")
    @PostMapping(value = "/girls/two")
    public void girlInsertTwo() {
        girlService.insertTwo();
    }

    @ApiOperation(value="修改女生信息", notes="根据url的id来修改一个女生信息")
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age,
                           @RequestParam("money") Long money) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        girl.setMoney(money);
        return girlRepository.save(girl);
    }

    @ApiOperation(value="删除女生信息", notes="根据url的id来删除一个女生信息")
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }

}
