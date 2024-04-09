package dh.project11.packs.simul.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dh.project11.packs.simul.object.TowerCondition;
import dh.project11.packs.simul.object.TowerCondition.AttackRoute;

@Component
public class AboutFieldLogic {
	
	private Random random = new Random();

	private int topFirstTowerU;
	private int topSecondTowerU; 
	private int topThirdTowerU;
	private int topInhibitorU;
	private int midFirstTowerU;
	private int midSecondTowerU; 
	private int midThirdTowerU;
	private int midInhibitorU;
	private int botFirstTowerU;
	private int botSecondTowerU; 
	private int botThirdTowerU;
	private int botInhibitorU;
	private int topFirstTowerE;
	private int topSecondTowerE; 
	private int topThirdTowerE;
	private int topInhibitorE; 
	private int midFirstTowerE;
	private int midSecondTowerE; 
	private int midThirdTowerE;
	private int midInhibitorE; 
	private int botFirstTowerE;
	private int botSecondTowerE; 
	private int botThirdTowerE;
	private int botInhibitorE; 
	
	public AboutFieldLogic(TowerCondition towerU, TowerCondition towerE) {

	    AttackRoute topU = towerU.getTop();
	    AttackRoute midU = towerU.getMid();
	    AttackRoute botU = towerU.getBot();
	    AttackRoute topE = towerE.getTop();
	    AttackRoute midE = towerE.getMid();
	    AttackRoute botE = towerE.getBot();
	    if(topU !=null) {
	    	this.topFirstTowerU = topU.getFristTower();
	    	this.topSecondTowerU = topU.getSecondTower();
	    	this.topThirdTowerU = topU.getThirdTower();
	    	this.topInhibitorU = topU.getInhibitor();
	    }else {
	    	this.topFirstTowerU = 1;
	    	this.topSecondTowerU = 1;
	    	this.topThirdTowerU = 1;
	    	this.topInhibitorU = 1;
	    }
	    if(midU !=null) {
	    	this.midFirstTowerU = midU.getFristTower();
	    	this.midSecondTowerU = midU.getSecondTower();
	    	this.midThirdTowerU = midU.getThirdTower();
	    	this.midInhibitorU = midU.getInhibitor();
	    }else {
	    	this.midFirstTowerU = 1;
	    	this.midSecondTowerU = 1;
	    	this.midThirdTowerU = 1;
	    	this.midInhibitorU = 1;
	    }
	    if(botU !=null) {
	    	this.botFirstTowerU = botU.getFristTower();
	    	this.botSecondTowerU = botU.getSecondTower();
	    	this.botThirdTowerU = botU.getThirdTower();
	    	this.botInhibitorU = botU.getInhibitor();
	    }else {
	    	this.botFirstTowerU = 1;
	    	this.botSecondTowerU = 1;
	    	this.botThirdTowerU = 1;
	    	this.botInhibitorU = 1;
	    }
	    if(topE !=null) {
	    	this.topFirstTowerE = topE.getFristTower();
	    	this.topSecondTowerE = topE.getSecondTower();
	    	this.topThirdTowerE = topE.getThirdTower();
	    	this.topInhibitorE = topE.getInhibitor();
	    }else {
	    	this.topFirstTowerE = 1;
	    	this.topSecondTowerE = 1;
	    	this.topThirdTowerE = 1;
	    	this.topInhibitorE = 1;
	    }
	    if(midE !=null) {
	    	this.midFirstTowerE = midE.getFristTower();
	    	this.midSecondTowerE = midE.getSecondTower();
	    	this.midThirdTowerE = midE.getThirdTower();
	    	this.midInhibitorE = midE.getInhibitor();
	    }else {
	    	this.midFirstTowerE = 1;
	    	this.midSecondTowerE = 1;
	    	this.midThirdTowerE = 1;
	    	this.midInhibitorE = 1;
	    }
	    if(botE !=null) {
	    	this.botFirstTowerE = botE.getFristTower();
	    	this.botSecondTowerE = botE.getSecondTower();
	    	this.botThirdTowerE = botE.getThirdTower();
	    	this.botInhibitorE = botE.getInhibitor();
	    }else {
	    	this.botFirstTowerE = 1;
	    	this.botSecondTowerE = 1;
	    	this.botThirdTowerE = 1;
	    	this.botInhibitorE = 1;
	    }
	    System.out.println(topFirstTowerU);
		System.out.println(topSecondTowerU); 
		System.out.println(topThirdTowerU);
		System.out.println(topInhibitorU);
		System.out.println(midFirstTowerU);
		System.out.println(midSecondTowerU); 
		System.out.println(midThirdTowerU);
		System.out.println(midInhibitorU);
		System.out.println(botFirstTowerU);
		System.out.println(botSecondTowerU); 
		System.out.println(botThirdTowerU);
		System.out.println(botInhibitorU);
		System.out.println(topFirstTowerE);
		System.out.println(topSecondTowerE); 
		System.out.println(topThirdTowerE);
		System.out.println(topInhibitorE); 
		System.out.println(midFirstTowerE);
		System.out.println(midSecondTowerE); 
		System.out.println(midThirdTowerE);
		System.out.println(midInhibitorE); 
		System.out.println(botFirstTowerE);
		System.out.println(botSecondTowerE); 
		System.out.println(botThirdTowerE);
		System.out.println(botInhibitorE); 
	}
	
	public String battleFieldSetting(boolean baron, boolean dragon, boolean elder) {
		List<String> fieldList = new ArrayList<>();
		fieldList.add("freeze");
		fieldList.add("freeze");
		fieldList.add("freeze");
		fieldList.add("midLine");
		fieldList.add("topLine");
		fieldList.add("bottomLine");
		fieldList.add("baronNest");
		fieldList.add("dragonNest");
		
		fieldList.add("topFirstTowerU");
		if(topFirstTowerU==0) {
			fieldList.add("topSecondTowerU");
		}
		if(topSecondTowerU==0) {
			fieldList.add("topThirdTowerU");
		}
		if(topThirdTowerU==0) {
			fieldList.add("topInhibitorU");
		}
		if(topInhibitorU==0) {
			fieldList.add("baseU");
		}
		fieldList.add("midFirstTowerU");
		if(midFirstTowerU==0) {
			fieldList.add("midSecondTowerU");
		}
		if(midSecondTowerU==0) {
			fieldList.add("midThirdTowerU");
		}
		if(midThirdTowerU==0) {
			fieldList.add("midInhibitorU");
		}
		if(midInhibitorU==0) {
			fieldList.add("baseU");
		}
		fieldList.add("botFirstTowerU");
		if(botFirstTowerU==0) {
			fieldList.add("botSecondTowerU");
		}
		if(botSecondTowerU==0) {
			fieldList.add("botThirdTowerU");
		}
		if(botThirdTowerU==0) {
			fieldList.add("botInhibitorU");
		}
		if(botInhibitorU==0) {
			fieldList.add("baseU");
		}
		
		fieldList.add("topFirstTowerE");
		if(topFirstTowerE==0) {
			fieldList.add("topSecondTowerE");
		}
		if(topSecondTowerE==0) {
			fieldList.add("topThirdTowerE");
		}
		if(topThirdTowerE==0) {
			fieldList.add("topInhibitorE");
		}
		if(topInhibitorE==0) {
			fieldList.add("baseE");
		}
		fieldList.add("midFirstTowerE");
		if(midFirstTowerE==0) {
			fieldList.add("midSecondTowerE");
		}
		if(midSecondTowerE==0) {
			fieldList.add("midThirdTowerE");
		}
		if(midThirdTowerE==0) {
			fieldList.add("midInhibitorE");
		}
		if(midInhibitorE==0) {
			fieldList.add("baseE");
		}
		fieldList.add("botFirstTowerE");
		if(botFirstTowerE==0) {
			fieldList.add("botSecondTowerE");
		}
		if(botSecondTowerE==0) {
			fieldList.add("botThirdTowerE");
		}
		if(botThirdTowerE==0) {
			fieldList.add("botInhibitorE");
		}
		if(botInhibitorE==0) {
			fieldList.add("baseE");
		}
		if(baron==true) {
			fieldList.add("baronNest");
			fieldList.add("baronNest");
			fieldList.add("baronNest");
			fieldList.add("baronNest");
		}
		if(dragon==true) {
			fieldList.add("dragonNest");
			fieldList.add("dragonNest");
		}
		if(elder==true) {
			for(int i=0; i<10; i++) {
				fieldList.add("dragonNest");
			}
		}
		for (String item : fieldList) {
            System.out.println(item);
        }
		int size =fieldList.size();
		System.out.println(size-1);
		int field = 0;
		field = random.nextInt(size-1);
		return fieldList.get(field);
	}
}
