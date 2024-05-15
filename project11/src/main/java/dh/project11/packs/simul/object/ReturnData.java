package dh.project11.packs.simul.object;

import java.util.ArrayList;
import java.util.List;

public class ReturnData {
	
    private int round;
    private int killU;
    private int killE;
    private int towerU;
    private int towerE;
    private int baronU;
    private int baronE;
    private int elderU;
    private int elderE;
    private List<String> dragonU = new ArrayList<>();
    private List<String> dragonE = new ArrayList<>();
    private int nexusU;
    private int t1U;
    private int t2U;
    private int t3U;
    private int tiU;
    private int m1U;
    private int m2U;
    private int m3U;
    private int miU;
    private int b1U;
    private int b2U;
    private int b3U;
    private int biU;
    private int ltU;
    private int rtU;
    private int baronNest;
    private String dragonNest;
    private int nexusE;
    private String field;
    private int t1E;
    private int t2E;
    private int t3E;
    private int tiE;
    private int m1E;
    private int m2E;
    private int m3E;
    private int miE;
    private int b1E;
    private int b2E;
    private int b3E;
    private int biE;
    private int ltE;
    private int rtE;
    private int c1u;
    private int c2u;
    private int c3u;
    private int c4u;
    private int c5u;
    private int c1e;
    private int c2e;
    private int c3e;
    private int c4e;
    private int c5e;
    
    public ReturnData() {
		// TODO Auto-generated constructor stub
	}
	public ReturnData(ReturnData rDataM, List<String> dU, List<String> dE) {
		// TODO Auto-generated constructor stub
		this.round = rDataM.round;
		this.killU = rDataM.killU;
		this.killE = rDataM.killE;
		this.towerU = rDataM.towerU;
		this.towerE = rDataM.towerE;
		this.baronU = rDataM.baronU;
		this.baronE = rDataM.baronE;
		this.elderU = rDataM.elderU;
		this.elderE = rDataM.elderE;
		this.dragonU = dU;
		this.dragonE = dE;
		this.nexusU = rDataM.nexusU;
		this.t1U = rDataM.t1U;
		this.t2U = rDataM.t2U;
		this.t3U = rDataM.t3U;
		this.tiU = rDataM.tiU;
		this.m1U = rDataM.m1U;
		this.m2U = rDataM.m2U;
		this.m3U = rDataM.m3U;
		this.miU = rDataM.miU;
		this.b1U = rDataM.b1U;
		this.b2U = rDataM.b2U;
		this.b3U = rDataM.b3U;
		this.biU = rDataM.biU;
		this.ltU = rDataM.ltU;
		this.rtU = rDataM.rtU;
		this.baronNest = rDataM.baronNest;
		this.dragonNest = rDataM.dragonNest;
		this.nexusE = rDataM.nexusE;
		this.field = rDataM.field;
		this.t1E = rDataM.t1E;
		this.t2E = rDataM.t2E;
		this.t3E = rDataM.t3E;
		this.tiE = rDataM.tiE;
		this.m1E = rDataM.m1E;
		this.m2E = rDataM.m2E;
		this.m3E = rDataM.m3E;
		this.miE = rDataM.miE;
		this.b1E = rDataM.b1E;
		this.b2E = rDataM.b2E;
		this.b3E = rDataM.b3E;
		this.biE = rDataM.biE;
		this.ltE = rDataM.ltE;
		this.rtE = rDataM.rtE;
		this.c1u = rDataM.c1u;
		this.c2u = rDataM.c2u;
		this.c3u = rDataM.c3u;
		this.c4u = rDataM.c4u;
		this.c5u = rDataM.c5u;
		this.c1e = rDataM.c1e;
		this.c2e = rDataM.c2e;
		this.c3e = rDataM.c3e;
		this.c4e = rDataM.c4e;
		this.c5e = rDataM.c5e;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getKillU() {
		return killU;
	}
	public void setKillU(int killU) {
		this.killU = killU;
	}
	public int getKillE() {
		return killE;
	}
	public void setKillE(int killE) {
		this.killE = killE;
	}
	public int getTowerU() {
		return towerU;
	}
	public void setTowerU(int towerU) {
		this.towerU = towerU;
	}
	public int getTowerE() {
		return towerE;
	}
	public void setTowerE(int towerE) {
		this.towerE = towerE;
	}
	public int getBaronU() {
		return baronU;
	}
	public void setBaronU(int baronU) {
		this.baronU = baronU;
	}
	public int getBaronE() {
		return baronE;
	}
	public void setBaronE(int baronE) {
		this.baronE = baronE;
	}
	public int getElderU() {
		return elderU;
	}
	public void setElderU(int elderU) {
		this.elderU = elderU;
	}
	public int getElderE() {
		return elderE;
	}
	public void setElderE(int elderE) {
		this.elderE = elderE;
	}
	public List<String> getDragonU() {
		return dragonU;
	}
	public void setDragonU(List<String> dragonU) {
		this.dragonU = dragonU;
	}
	public List<String> getDragonE() {
		return dragonE;
	}
	public void setDragonE(List<String> dragonE) {
		this.dragonE = dragonE;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getNexusU() {
		return nexusU;
	}
	public void setNexusU(int nexusU) {
		this.nexusU = nexusU;
	}
	public int getT1U() {
		return t1U;
	}
	public void setT1U(int t1u) {
		t1U = t1u;
	}
	public int getT2U() {
		return t2U;
	}
	public void setT2U(int t2u) {
		t2U = t2u;
	}
	public int getT3U() {
		return t3U;
	}
	public void setT3U(int t3u) {
		t3U = t3u;
	}
	public int getTiU() {
		return tiU;
	}
	public void setTiU(int tiU) {
		this.tiU = tiU;
	}
	public int getM1U() {
		return m1U;
	}
	public void setM1U(int m1u) {
		m1U = m1u;
	}
	public int getM2U() {
		return m2U;
	}
	public void setM2U(int m2u) {
		m2U = m2u;
	}
	public int getM3U() {
		return m3U;
	}
	public void setM3U(int m3u) {
		m3U = m3u;
	}
	public int getMiU() {
		return miU;
	}
	public void setMiU(int miU) {
		this.miU = miU;
	}
	public int getB1U() {
		return b1U;
	}
	public void setB1U(int b1u) {
		b1U = b1u;
	}
	public int getB2U() {
		return b2U;
	}
	public void setB2U(int b2u) {
		b2U = b2u;
	}
	public int getB3U() {
		return b3U;
	}
	public void setB3U(int b3u) {
		b3U = b3u;
	}
	public int getBiU() {
		return biU;
	}
	public void setBiU(int biU) {
		this.biU = biU;
	}
	public int getLtU() {
		return ltU;
	}
	public void setLtU(int ltU) {
		this.ltU = ltU;
	}
	public int getRtU() {
		return rtU;
	}
	public void setRtU(int rtU) {
		this.rtU = rtU;
	}
	public int getBaronNest() {
		return baronNest;
	}
	public void setBaronNest(int baronNest) {
		this.baronNest = baronNest;
	}
	public String getDragonNest() {
		return dragonNest;
	}
	public void setDragonNest(String dragonNest) {
		this.dragonNest = dragonNest;
	}
	public int getNexusE() {
		return nexusE;
	}
	public void setNexusE(int nexusE) {
		this.nexusE = nexusE;
	}
	public int getT1E() {
		return t1E;
	}
	public void setT1E(int t1e) {
		t1E = t1e;
	}
	public int getT2E() {
		return t2E;
	}
	public void setT2E(int t2e) {
		t2E = t2e;
	}
	public int getT3E() {
		return t3E;
	}
	public void setT3E(int t3e) {
		t3E = t3e;
	}
	public int getTiE() {
		return tiE;
	}
	public void setTiE(int tiE) {
		this.tiE = tiE;
	}
	public int getM1E() {
		return m1E;
	}
	public void setM1E(int m1e) {
		m1E = m1e;
	}
	public int getM2E() {
		return m2E;
	}
	public void setM2E(int m2e) {
		m2E = m2e;
	}
	public int getM3E() {
		return m3E;
	}
	public void setM3E(int m3e) {
		m3E = m3e;
	}
	public int getMiE() {
		return miE;
	}
	public void setMiE(int miE) {
		this.miE = miE;
	}
	public int getB1E() {
		return b1E;
	}
	public void setB1E(int b1e) {
		b1E = b1e;
	}
	public int getB2E() {
		return b2E;
	}
	public void setB2E(int b2e) {
		b2E = b2e;
	}
	public int getB3E() {
		return b3E;
	}
	public void setB3E(int b3e) {
		b3E = b3e;
	}
	public int getBiE() {
		return biE;
	}
	public void setBiE(int biE) {
		this.biE = biE;
	}
	public int getLtE() {
		return ltE;
	}
	public void setLtE(int ltE) {
		this.ltE = ltE;
	}
	public int getRtE() {
		return rtE;
	}
	public void setRtE(int rtE) {
		this.rtE = rtE;
	}
	public int getC1u() {
		return c1u;
	}
	public void setC1u(int c1u) {
		this.c1u = c1u;
	}
	public int getC2u() {
		return c2u;
	}
	public void setC2u(int c2u) {
		this.c2u = c2u;
	}
	public int getC3u() {
		return c3u;
	}
	public void setC3u(int c3u) {
		this.c3u = c3u;
	}
	public int getC4u() {
		return c4u;
	}
	public void setC4u(int c4u) {
		this.c4u = c4u;
	}
	public int getC5u() {
		return c5u;
	}
	public void setC5u(int c5u) {
		this.c5u = c5u;
	}
	public int getC1e() {
		return c1e;
	}
	public void setC1e(int c1e) {
		this.c1e = c1e;
	}
	public int getC2e() {
		return c2e;
	}
	public void setC2e(int c2e) {
		this.c2e = c2e;
	}
	public int getC3e() {
		return c3e;
	}
	public void setC3e(int c3e) {
		this.c3e = c3e;
	}
	public int getC4e() {
		return c4e;
	}
	public void setC4e(int c4e) {
		this.c4e = c4e;
	}
	public int getC5e() {
		return c5e;
	}
	public void setC5e(int c5e) {
		this.c5e = c5e;
	}
	public void resetChamp() {
		// TODO Auto-generated method stub
		this.c1u = 1;
		this.c2u = 1;
		this.c3u = 1;
		this.c4u = 1;
		this.c5u = 1;
		this.c1e = 1;
		this.c2e = 1;
		this.c3e = 1;
		this.c4e = 1;
		this.c5e = 1;
	}
    
}
