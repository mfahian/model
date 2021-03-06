package com.malpro.model.controller;

import com.malpro.model.model.EtimClass;
import com.malpro.model.model.EtimGroup;
import com.malpro.model.service.IEtimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.malpro.model.configuration.ApiConfiguration.API_URI_V1;

/**
 * Created by martin.fahian on 04.02.21.
 */
@RestController
@RequestMapping(API_URI_V1 + EtimEntitiesController.URL)
public class EtimEntitiesController {
    public static final String URL = "/etim";

    @Autowired
    private IEtimService etimService;

    @GetMapping(value = "/group/{code}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<EtimGroup> getGroup(@PathVariable("code") String code) {
        Optional<EtimGroup> etimGroup = etimService.findEtimGroupByCode(code);
        if (etimGroup.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(etimGroup.get());
    }

    @GetMapping(value = "/class/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<EtimClass> getClassGroup(@PathVariable("code") String code) {
        Optional<EtimClass> etimClass = etimService.findEtimClassByCode(code);
        if (etimClass.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(etimClass.get());
    }


}
