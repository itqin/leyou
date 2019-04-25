package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qin
 * @create 2019-04-25 14:04
 */
@RestController
@RequestMapping("spec")
public class SpecifictionController {

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroups(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(this.specificationService.querySpecGroup(cid));
    }

    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroup(@RequestBody SpecGroup specGroup) {
        specificationService.addSpecGroup(specGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("group")
    public ResponseEntity<Void> updateSpecGroup(@RequestBody SpecGroup specGroup){
        specificationService.updateSpecGroup(specGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParams(@RequestParam("gid")Long gid){
        List<SpecParam> list = this.specificationService.querySpecParams(gid);
        return ResponseEntity.ok(list);
    }

    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody SpecParam specParam){
        this.specificationService.addSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam specParam){
        this.specificationService.updateSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable("id")Long id){
        this.specificationService.deleteSpecParam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
