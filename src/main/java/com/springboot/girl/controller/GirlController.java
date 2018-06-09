package com.springboot.girl.controller;

import com.springboot.girl.Girl;
import com.springboot.girl.GirlRepository;
import com.springboot.girl.GirlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @ApiOperation(value="查询所有女生", notes="")
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
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

    @ApiOperation(value = "增加一个女生", notes = "")
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age ) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
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
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    @ApiOperation(value="删除女生信息", notes="根据url的id来删除一个女生信息")
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

}
