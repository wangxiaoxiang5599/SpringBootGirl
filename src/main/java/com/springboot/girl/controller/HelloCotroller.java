package com.springboot.girl.controller;

import com.springboot.girl.properties.GirlProperties;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloCotroller {

    @Autowired
    private GirlProperties girlProperties;

    @ApiOperation(value="sayId", notes="")
    @ApiImplicitParam(name = "id", value = "你好", required = true, dataType = "String",paramType = "path")
    //@RequestMapping(value = "/say/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/say{id}")
    public String say(@PathVariable("id") Integer id) {

        return "id:" + id;
    }

    @ApiOperation(value="sayProperties", notes="")
    @GetMapping(value = "/say")
    public String say() {
        return girlProperties.getCupSize();
    }
}
