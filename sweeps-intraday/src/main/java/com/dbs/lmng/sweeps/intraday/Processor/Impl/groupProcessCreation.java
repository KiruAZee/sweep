package com.dbs.lmng.sweeps.intraday.Processor.Impl;

import com.dbs.lmng.sweeps.intraday.Elements.Account;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDayBusinessTime;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroup;
import com.dbs.lmng.sweeps.intraday.Processor.IGroupProcessCreation;
import com.dbs.lmng.sweeps.intraday.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class groupProcessCreation implements IGroupProcessCreation {
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
    @Autowired
    IntraDaySweepMasterRepository intraDaySweepMasterRepository;

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    RepositoryService repositoryService;

    @Override
    public void loadProcesses(IntraDayTime intraDayTime) {
        /*
            For every group created, trigger sub process & capture the process id.
         */
        log.info("groupProcessCreation: " + intraDayTime);

        List<SweepGroup> independentGroupList = sweepGroupRepository.findByHoldingGroupId(null);
//        List<SweepGroup> independentGroupList = sweepGroupRepository.findByHoldingGroupId(new Account("B","SG"));

        independentGroupList.forEach( (group) -> {
                    log.info("group process is " + group.getSweepGroupKey());
                    String groupProcess = createGroupProcess(group);
                    log.info("sub process is " + groupProcess);
                    group.setSubProcessId(groupProcess);
                    sweepGroupRepository.save(group);
        });
//        List<SweepGroup> byHoldingGroupId = sweepGroupRepository.findByHoldingGroupId(new Account("B","SG"));
//        log.info("byHoldingGroupId is : " + byHoldingGroupId);


//        IntraDaySweepMasterKey intraDaySweepMasterKey = new IntraDaySweepMasterKey();
//        intraDaySweepMasterKey.setIntraDayBusinessTime(intraDayTime.getIntraDayBusinessTime());
//        intraDaySweepMasterKey.setBankId(intraDayTime.getBankId());
//        Optional<IntraDaySweepMaster> intraDaySweepMaster = intraDaySweepMasterRepository.findById(intraDaySweepMasterKey);
//
//        if (intraDaySweepMaster.isEmpty()) {
//            // Fatal
//        }
//
//        intraDaySweepMaster.get();

//        sweepGroupRepository.findByIntraDayBusinessTime(intraDayTime.getIntraDayBusinessTime());

    }

    String createGroupProcess(SweepGroup sweepGroup) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("group", sweepGroup.getSweepGroupKey());

        System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
        return runtimeService.startProcessInstanceByKey("IntraDayGroups", variables).getId();
    }
}
