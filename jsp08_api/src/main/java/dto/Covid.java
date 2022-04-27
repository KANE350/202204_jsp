package dto;

public class Covid {
	private int seq;
	private String stateDt;
	private String stateTime;
	private int decideCnt;	//누적확진자수
	private int dailyDecideCnt;	//일일 확진자수
	private int deathCnt;
	private String createDt;
	private String updateDt;
	public Covid() {
		super();
	}
	
	public Covid(int seq, String stateDt, String stateTime, int decideCnt, int dailyDecideCnt, int deathCnt,
			String createDt, String updateDt) {
		super();
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getStateDt() {
		return stateDt;
	}

	public void setStateDt(String stateDt) {
		this.stateDt = stateDt;
	}

	public String getStateTime() {
		return stateTime;
	}

	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}

	public int getDecideCnt() {
		return decideCnt;
	}

	public void setDecideCnt(int decideCnt) {
		this.decideCnt = decideCnt;
	}

	public int getDailyDecideCnt() {
		return dailyDecideCnt;
	}

	public void setDailyDecideCnt(int dailyDecideCnt) {
		this.dailyDecideCnt = dailyDecideCnt;
	}

	public int getDeathCnt() {
		return deathCnt;
	}

	public void setDeathCnt(int deathCnt) {
		this.deathCnt = deathCnt;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}

	@Override
	public String toString() {
		return "Covid [seq=" + seq + ", stateDt=" + stateDt + ", stateTime=" + stateTime + ", decideCnt=" + decideCnt
				+ ", dailyDecideCnt=" + dailyDecideCnt + ", deathCnt=" + deathCnt + ", createDt=" + createDt
				+ ", updateDt=" + updateDt + "]";
	}
	
	
	}
	