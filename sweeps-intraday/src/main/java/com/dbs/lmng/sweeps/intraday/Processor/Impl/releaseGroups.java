package com.dbs.lmng.sweeps.intraday.Processor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;
import com.dbs.lmng.sweeps.intraday.Processor.IReleaseDependentGroup;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class releaseGroups implements IReleaseDependentGroup {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    RepositoryService repositoryService;

    @Override
    public void release(IntraDayTime intraDayTime) {
        log.info("triggerDependentGroup: " + intraDayTime);
        // Release all the dependent groups.
//        Map<String, Object> variables = new HashMap<String, Object>();
//        new SweepGroup();
//        variables.put("Group", intraDayTime);
//
//        System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
//        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//        String id = runtimeService.startProcessInstanceByKey("IntraDayGroups", variables).getId();
//        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
////        log.info(" var is " + runtimeService.getVariable(id,"myVar"));
//
//        Execution execution = runtimeService.createExecutionQuery()
//                .processInstanceId(id)
//                .activityId("WaitForSweepCompletion")
//                .singleResult();
//
//        runtimeService.trigger(execution.getId());

//            runtimeService.signalEventReceived();
        // Release all independent groups.
    }
}
