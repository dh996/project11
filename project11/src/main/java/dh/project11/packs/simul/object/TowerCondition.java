package dh.project11.packs.simul.object;

import org.springframework.stereotype.Component;

@Component
public class TowerCondition {

	private AttackRoute top;
	private AttackRoute mid;
	private AttackRoute bot;
	private int twinTowerLeft;
	private int twinTowerRight;
	private int nexus;

	public static class AttackRoute{
		private int fristTower;
		private int secondTower;
		private int thirdTower;
		private int inhibitor;
		
		public int getFristTower() {
			return fristTower;
		}
		public void setFristTower(int fristTower) {
			this.fristTower = fristTower;
		}
		public int getSecondTower() {
			return secondTower;
		}
		public void setSecondTower(int secondTower) {
			this.secondTower = secondTower;
		}
		public int getThirdTower() {
			return thirdTower;
		}
		public void setThirdTower(int thirdTower) {
			this.thirdTower = thirdTower;
		}
		public int getInhibitor() {
			return inhibitor;
		}
		public void setInhibitor(int inhibitor) {
			this.inhibitor = inhibitor;
		}
	}
	
	public AttackRoute getTop() {
		return top;
	}

	public void setTop(AttackRoute top) {
		this.top = top;
	}

	public AttackRoute getMid() {
		return mid;
	}

	public void setMid(AttackRoute mid) {
		this.mid = mid;
	}

	public AttackRoute getBot() {
		return bot;
	}

	public void setBot(AttackRoute bot) {
		this.bot = bot;
	}

	public int getTwinTowerLeft() {
		return twinTowerLeft;
	}

	public void setTwinTowerLeft(int twinTowerLeft) {
		this.twinTowerLeft = twinTowerLeft;
	}

	public int getTwinTowerRight() {
		return twinTowerRight;
	}

	public void setTwinTowerRight(int twinTowerRight) {
		this.twinTowerRight = twinTowerRight;
	}

	public int getNexus() {
		return nexus;
	}

	public void setNexus(int nexus) {
		this.nexus = nexus;
	}
}
