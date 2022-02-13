package com.ginspiration.serverbackground.controller;


import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;
import com.ginspiration.serverbackground.service.IMaintainerService;
import com.ginspiration.serverbackground.vo.GetIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */

@Controller
@RequestMapping("/maintainer")
public class MaintainerController {
    @Autowired
    private IMaintainerService maintainerService;

    @PostMapping("/getAllMaintainerInfo")
    @ResponseBody
    public RespCommon getAllMaintainer(){
       return maintainerService.getAllMaintainer();
    }
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public RespCommon saveOrUpdate(@RequestBody Maintainer maintainer){
        maintainerService.maintainerSaveOrUpdate(maintainer);
        return new RespCommon(200,"操作成功");
    }
    @PostMapping("/del")
    @ResponseBody
    public RespCommon del(@RequestBody GetIdVo getIdVo){
        maintainerService.del(getIdVo.getId());
        return new RespCommon(200,"操作成功");
    }
}

