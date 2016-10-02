package weibo4j.examples.timeline;

import weibo4j.Timeline;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class QueryId {
	String access_token = "2.00_7C8gFOxK_cD9ec28c4cff8Y6FyB";
	
	 public String id1;
	 public String id2;
	public  void getId(String a){
		Timeline tm = new Timeline(access_token);
		try {
			JSONObject id = tm.queryId( a, 1,1);
			id1 = id.toString();
			id2 = id1.substring(1, id1.length() - 1);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		

	}

}
