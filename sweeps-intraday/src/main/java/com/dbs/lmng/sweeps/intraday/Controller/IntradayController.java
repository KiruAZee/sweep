package com.dbs.lmng.sweeps.intraday.Controller;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDayBusinessTime;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;
import com.dbs.lmng.sweeps.intraday.Entity.Linkage;
import com.dbs.lmng.sweeps.intraday.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class IntradayController {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    RepositoryService repositoryService;

    @Autowired
    StructureRepository structureRepository;
    @Autowired
    LinkageRepository linkageRepository;
    @Autowired
    SweepGroupRepository sweepGroupRepository;
    @Autowired
    SweepGroupLinkageRepository sweepGroupLinkageRepository;
    @Autowired
    IntraDayTimeRepository intraDayTimeRepository;

    @GetMapping("/intraday")
    public String uploadProcess(){
        Map<String, Object> variables = new HashMap<String, Object>();
        IntraDayTime intraDayTime = new IntraDayTime(
                                    new IntraDayBusinessTime(LocalDate.now(), "1400"),
                                    LocalDateTime.now());
        intraDayTime.setBankId("SG");
        log.info("intraDayTime before setting is "+ intraDayTime);
        variables.put("IntraDayTime", intraDayTime);

        System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
        String id = runtimeService.startProcessInstanceByKey("IntraDaySweeps", variables).getId();
        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//        log.info(" var is " + runtimeService.getVariable(id,"myVar"));

//        Execution execution = runtimeService.createExecutionQuery()
//                .processInstanceId(id)
//                .activityId("WaitForSweepCompletion")
//                .singleResult();
//
//        runtimeService.trigger(execution.getId());
        return "job started" + id;
    }

    @PostMapping("/linksweep")
    public ResponseEntity<Linkage> createLinkage(@RequestBody Linkage linkage){
        System.out.println("Input Received as : ==>"+linkage);
        linkage.getUserAudit().setCreatedOn(LocalTime.now());
        linkage.getUserAudit().setModifiedOn(LocalTime.now());

        return new ResponseEntity<Linkage>(linkageRepository.save(linkage), HttpStatus.OK);
    }
}
