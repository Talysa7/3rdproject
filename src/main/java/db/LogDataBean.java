package db;

import org.json.simple.JSONObject;

public class LogDataBean {
	private int log_id;
	private JSONObject log_str;
	
	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	

	public JSONObject getLog_str() {
		return log_str;
	}

	public void setLog_str(JSONObject log_str) {
		this.log_str = log_str;
	}
}
