package com.dbs.lmng.sweeps.intraday.Processor.Impl;

import com.dbs.lmng.sweeps.intraday.Elements.*;
import com.dbs.lmng.sweeps.intraday.Entity.*;
import com.dbs.lmng.sweeps.intraday.Processor.IEligibleStructureProcessor;
import com.dbs.lmng.sweeps.intraday.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class eligibleStructureProcessor implements IEligibleStructureProcessor{

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
    IntraDaySweepTimeLinkageRepository intraDaySweepTimeLinkageRepository;
    @Autowired
    IntraDaySweepMasterRepository intraDaySweepMasterRepository;

    @Override
    public Boolean prepareSweepTree(IntraDayTime intraDayTime, Execution execution) {
        log.info("eligibleStructureProcessor: " + intraDayTime);
        /*
            1. List<Linkage> allLink 	= select * from linkage where time=intraDayTime.getBusinessTime();
	        2. Set<parent> parentLink	= allLink.map(Linkage::getParent);
	        3. List<Group> 	groupList	= parentLink.map( (parent) -> {
			                                List<Linkage> linkageList = allLink.filter(Linkage.getParent()==parent);
		                                    Set<Account> DependencyList = linkageList.filter( (linkage) -> {
											                                return parentLink.contains(linkage.getChild());
										                                }).map(Linkage::getChild);
                                            // Create new group.
                                            return group;
                                          });
	        6. Store groupList in DB.
         */
        IntraDaySweepMaster intraDaySweepMaster = new IntraDaySweepMaster();
        IntraDaySweepMasterKey intraDaySweepMasterKey = new IntraDaySweepMasterKey();
        intraDaySweepMasterKey.setIntraDayBusinessTime(intraDayTime.getIntraDayBusinessTime());
        intraDaySweepMasterKey.setBankId(intraDayTime.getBankId());
        intraDaySweepMaster.setIntraDaySweepMasterKey(intraDaySweepMasterKey);
        intraDaySweepMaster.setProcessId(execution.getProcessInstanceId());
        intraDaySweepMaster.setStartTime(intraDayTime.getSystemDateTime());
        intraDaySweepMaster.setStatus("SWEEP_TREE_PREP");
        intraDaySweepMasterRepository.save(intraDaySweepMaster);

        IntraDaySweepTimeKey intraDaySweepTimeKey = new IntraDaySweepTimeKey();
        intraDaySweepTimeKey.setSweepTime(intraDayTime.getIntraDayBusinessTime().getBusinessTime());
        intraDaySweepTimeKey.setBankId(intraDayTime.getBankId());
        Optional<IntraDaySweepTime> linkageListByTime = intraDayTimeRepository.findById(intraDaySweepTimeKey);

        if (linkageListByTime.isEmpty()) {
            // No Linkage for processing. exit
            log.info(" NO records for processing");
            return true;
        }

        List<Linkage> intraDaySweepTimeLinkageList = linkageListByTime.get().getLinkageList();

        List<LinkageKey> linkageKeyList = intraDaySweepTimeLinkageList.stream()
                .map((intraDaySweepTimeLinkage) -> intraDaySweepTimeLinkage.getLinkageKey())
                .collect(Collectors.toList());

        log.info("linkageKeyList : " + linkageKeyList);
        log.info("intraDayTime.getIntraDayBusinessTime().getBusinessTime() is " + intraDayTime.getIntraDayBusinessTime().getBusinessTime());
        log.info("intraDayTime.getBankId() is "+ intraDayTime.getBankId());
//        List<Long> linkageIdList = intraDaySweepTimeLinkageRepository.findBySweepTimeAndBankId(
//                intraDayTime.getIntraDayBusinessTime().getBusinessTime(),
//                intraDayTime.getBankId());
//
//        log.info("linkageList is" + linkageIdList);
//        List<LinkageKey> linkageKeyList = linkageIdList.stream()
//                .map((linkageId) -> {
//                    LinkageKey linkageKey = new LinkageKey(linkageId);
//                    linkageKey.setBankId("SG");
//                    return linkageKey;
//                })
//                .collect(Collectors.toList());

        List<Linkage> allLinkageList = linkageRepository.findAllById(linkageKeyList);
        log.info("allLinkageList is " + allLinkageList);

        Set<Account> parentSet = allLinkageList.stream()
                .map(Linkage::getParentAcct)
                .collect(Collectors.toSet());
        log.info("parentSet is " + parentSet);

        List<SweepGroup> sweepGroupList = parentSet.stream()
                .map((parent) -> {
                    List<SweepGroupLinkage> sweepGroupLinkageList = allLinkageList.stream()
                            .filter((linkage) -> linkage.getParentAcct().equals(parent))
                            .map((linkage -> {
                                SweepGroupLinkage sweepGroupLinkage = new SweepGroupLinkage();
                                SweepGroupLinkageKey sweepGroupLinkageKey = new SweepGroupLinkageKey();
                                sweepGroupLinkageKey.setLinkageId(linkage.getLinkageKey().getLinkageId());
                                sweepGroupLinkageKey.setBankId(linkage.getLinkageKey().getBankId());
                                sweepGroupLinkageKey.setProcessId(execution.getProcessInstanceId());
//                                sweepGroupLinkageKey.setIntraDayBusinessTime(intraDayTime.getIntraDayBusinessTime());
                                sweepGroupLinkage.setSweepGroupLinkageKey(sweepGroupLinkageKey);
                                return sweepGroupLinkage;
                            }))
                            .collect(Collectors.toList());

                    List<Account> holdingGroupList = allLinkageList.stream()
                            .filter((linkage -> linkage.getLinkageDet().getChild().equals(parent)))
                            .map(Linkage::getParentAcct)
                            .collect(Collectors.toList());

                    log.info("parent is : " + parent);
                    log.info("linkage is : " + sweepGroupLinkageList);
                    log.info("holdingParent is " + holdingGroupList);

                    SweepGroupKey sweepGroupKey = new SweepGroupKey();
                    sweepGroupKey.setSweepGroupId(parent);
//                    sweepGroupKey.setBankId("SG");

                    StructureId structureId = new StructureId();
                    // Hardcoding to be removed.
                    structureId.setStructureId("S1");
                    structureId.setBankId("SG");
//                    SweepGroupDet sweepGroupDet = new SweepGroupDet();
                    sweepGroupKey.setStructureId(structureId);

                    SweepGroup sweepGroup = new SweepGroup();
                    sweepGroup.setSweepGroupKey(sweepGroupKey);
//                    sweepGroup.setSweepGroupDet(sweepGroupDet);
//                    sweepGroup.setIntraDayBusinessTime(intraDayTime.getIntraDayBusinessTime());
                    sweepGroup.setProcessId(execution.getProcessInstanceId());
                    sweepGroup.setSystemDateTime(intraDayTime.getSystemDateTime());
                    sweepGroup.setSystemAudit(new SystemAudit(LocalTime.now(),"SYSTEM"));

//                    sweepGroupLinkageList.forEach(sweepGroup::addLinkage);
                    sweepGroup.addLinkages(sweepGroupLinkageList);
//                    sweepGroup.getSweepGroupLinkageList().addAll(sweepGroupLinkageList);
                    if (!holdingGroupList.isEmpty())  sweepGroup.setHoldingGroupId(holdingGroupList.get(0));
//                    else sweepGroup.setHoldingGroupId(new Account("",""));

                    return sweepGroup;
                })
                .collect(Collectors.toList());

        log.info("sweepGroupList is " + sweepGroupList);
        sweepGroupRepository.saveAll(sweepGroupList);
        return true;
    }

    @PostConstruct
    void loadData() {
        Structure structure = new Structure();
        StructureId structureId = new StructureId();
        structureId.setStructureId("S1");
        structureId.setBankId("SG");
        structure.setStructureId(structureId);

        UserAudit userAudit = new UserAudit(LocalTime.now(), "SYSTEM", LocalTime.now(), "SYSTEM");
        structure.setUserAudit(userAudit);
        structureRepository.save(structure);

        Frequency frequency = new Frequency();
        frequency.setFreq('D');

        IntraDaySweepTimeKey intraDaySweepTimeKey = new IntraDaySweepTimeKey();
        intraDaySweepTimeKey.setSweepTime("1400");
        intraDaySweepTimeKey.setBankId("SG");
        IntraDaySweepTime intraDaySweepTime = new IntraDaySweepTime();
        intraDaySweepTime.setIntraDaySweepTimeKey(intraDaySweepTimeKey);

        long count = 0;
        Linkage linkage = new Linkage();
        setLinkageStaticData(linkage,frequency,userAudit,structureId);

        linkage.setParentAcct(new Account("A","SG"));
        linkage.getLinkageKey().setLinkageId(++count);
        linkage.getLinkageDet().setChild(new Account("B","SG"));
        intraDaySweepTime.getLinkageList().add(linkage);
        linkage.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage);

        Linkage linkage1 = new Linkage();
        /*LinkageKey linkageKey1 = new LinkageKey();
        linkageKey1.setBankId("SG");
        linkage1.setLinkageKey(linkageKey1);*/
        setLinkageStaticData(linkage1,frequency,userAudit,structureId);

        linkage1.setParentAcct(linkage.getParentAcct());
        linkage1.getLinkageKey().setLinkageId(++count);
        linkage1.getLinkageDet().setChild(new Account("C","SG"));
        intraDaySweepTime.getLinkageList().add(linkage1);
        linkage1.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage1);


        Linkage linkage2 = new Linkage();
        /*LinkageKey linkageKey2 = new LinkageKey();
        linkageKey2.setBankId("SG");
        linkage2.setLinkageKey(linkageKey2);*/
        setLinkageStaticData(linkage2,frequency,userAudit,structureId);

        linkage2.setParentAcct(new Account("B","SG"));
        linkage2.getLinkageKey().setLinkageId(++count);
        linkage2.getLinkageDet().setChild(new Account("D","SG"));
        intraDaySweepTime.getLinkageList().add(linkage2);
        linkage2.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage2);

        Linkage linkage3 = new Linkage();
       /* LinkageKey linkageKey3 = new LinkageKey();
        linkageKey3.setBankId("SG");
        linkage3.setLinkageKey(linkageKey3);*/
        setLinkageStaticData(linkage3,frequency,userAudit,structureId);


        linkage3.setParentAcct(linkage2.getParentAcct());
        linkage3.getLinkageKey().setLinkageId(++count);
        linkage3.getLinkageDet().setChild(new Account("E","SG"));
        intraDaySweepTime.getLinkageList().add(linkage3);
        linkage3.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage3);


        Linkage linkage4 = new Linkage();
        setLinkageStaticData(linkage4,frequency,userAudit,structureId);

        linkage4.setParentAcct(new Account("C","SG"));
        linkage4.getLinkageKey().setLinkageId(++count);
        linkage4.getLinkageDet().setChild(new Account("F","SG"));
        intraDaySweepTime.getLinkageList().add(linkage4);
        linkage4.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage4);

        Linkage linkage5 = new Linkage();
        setLinkageStaticData(linkage5,frequency,userAudit,structureId);

        linkage5.setParentAcct(new Account("D","SG"));
        linkage5.getLinkageKey().setLinkageId(++count);
        linkage5.getLinkageDet().setChild(new Account("G","SG"));
        intraDaySweepTime.getLinkageList().add(linkage5);
        linkage5.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage5);

        Linkage linkage6 = new Linkage();
        setLinkageStaticData(linkage6,frequency,userAudit,structureId);

        linkage6.setParentAcct(linkage5.getParentAcct());
        linkage6.getLinkageKey().setLinkageId(++count);
        linkage6.getLinkageDet().setChild(new Account("H","SG"));
        intraDaySweepTime.getLinkageList().add(linkage6);
        linkage6.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage6);

        Linkage linkage7 = new Linkage();
        setLinkageStaticData(linkage7,frequency,userAudit,structureId);

        linkage7.setParentAcct(new Account("E","SG"));
        linkage7.getLinkageKey().setLinkageId(++count);
        linkage7.getLinkageDet().setChild(new Account("I","SG"));
        intraDaySweepTime.getLinkageList().add(linkage7);
        linkage7.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage7);

        Linkage linkage8 = new Linkage();
        setLinkageStaticData(linkage8,frequency,userAudit,structureId);

        linkage8.setParentAcct(linkage7.getParentAcct());
        linkage8.getLinkageKey().setLinkageId(++count);
        linkage8.getLinkageDet().setChild(new Account("J","SG"));
        intraDaySweepTime.getLinkageList().add(linkage8);
        linkage8.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage8);

        Linkage linkage9 = new Linkage();
        setLinkageStaticData(linkage9,frequency,userAudit,structureId);

        linkage9.setParentAcct(new Account("F","SG"));
        linkage9.getLinkageKey().setLinkageId(++count);
        linkage9.getLinkageDet().setChild(new Account("K","SG"));
        intraDaySweepTime.getLinkageList().add(linkage9);
        linkage9.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage9);

        Linkage linkage10 = new Linkage();
        setLinkageStaticData(linkage10,frequency,userAudit,structureId);

        linkage10.setParentAcct(linkage9.getParentAcct());
        linkage10.getLinkageKey().setLinkageId(++count);
        linkage10.getLinkageDet().setChild(new Account("L","SG"));
        intraDaySweepTime.getLinkageList().add(linkage10);
        linkage10.getIntraDaySweepTimeList().add(intraDaySweepTime);
        linkageRepository.save(linkage10);
    }

    private void setLinkageStaticData(Linkage linkage,Frequency frequency, UserAudit userAudit,
                                      StructureId structureId){
        LinkageKey linkageKey = new LinkageKey();
        //linkageKey.setLinkageId(++count);
        linkageKey.setBankId("SG");
        linkage.setLinkageKey(linkageKey);

        linkage.setFrequency(frequency);
        linkage.setUserAudit(userAudit);

        LinkageDet linkageDet = new LinkageDet();
        linkageDet.setStructureId(structureId);
        linkage.setLinkageDet(linkageDet);

        linkage.setBalanceFrom(new Amount(BigDecimal.ZERO,"SGD"));
        linkage.setBalanceTo(new Amount(BigDecimal.ZERO,"SGD"));

    }
}
/*
{
	"linkageKey": {
		"linkageId": "12",
		"bankId": "SG"
	},
	"linkageDet": {
		"structureId": {
			"structureId": "S1",
			"bankId": "SG"
		},
		"child": {
			"acctId": "ACC01-1",
			"acctCntry": "SG"
		}
	},
	"parentAcct": {
		"acctId": "ACC01",
		"acctCntry": "SG"
	},
	"priority": 0,
	"frequency": {
        "freq": "D",
        "freqDay" : null,
        "freqWeekDay": null,
        "hldyFlg": null
    },
	"nextSweepDate": "",
	"intRate": {},
	"sweepCentralMthd": "",
	"balanceFrom": {
		"asset": 0,
		"denomination": "SGD"
	},
	"balanceTo": {
		"asset": 0,
		"denomination": "SGD"
	},
	"p2pTTFee": "",
	"allowPartialSweep": "",
	"allowNCBalanceReset": "",
	"forceDebit": "",
	"intraDaySweepTimeList": [
		{
			"intraDaySweepTimeKey": {
				"sweepTime": "2100",
				"bankId": "SG"
			}
		},
        {
			"intraDaySweepTimeKey": {
				"sweepTime": "2300",
				"bankId": "SG"
			}
		}
	],
	"userAudit": {
		"createdOn": "",
		"createdBy": "SYSTEM",
		"modifiedOn": "",
		"modifiedBy": "SYSTEM"
	}
}
 */