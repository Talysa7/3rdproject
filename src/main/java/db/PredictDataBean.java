package db;

public class PredictDataBean {
	
	private String user_id;
	private int predict1;
	private int predict2;
	private int predict3;
	private String predictName;
	private int clusterNo;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPredict1() {
		return predict1;
	}

	public void setPredict1(int predict1) {
		this.predict1 = predict1;
	}

	public int getPredict2() {
		return predict2;
	}

	public void setPredict2(int predict2) {
		this.predict2 = predict2;
	}

	public int getPredict3() {
		return predict3;
	}

	public void setPredict3(int predict3) {
		this.predict3 = predict3;
	}

	public String getPredictName() {
		return predictName;
	}

	public void setPredictName(String predictName) {
		this.predictName = predictName;
	}

	public int getClusterNo() {
		return clusterNo;
	}

	public void setClusterNo(int clusterNo) {
		this.clusterNo = clusterNo;
	}
	
	
}
