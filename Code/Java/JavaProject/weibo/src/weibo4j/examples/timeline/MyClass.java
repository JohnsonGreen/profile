package weibo4j.examples.timeline;

import java.util.HashMap;
import java.util.List;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Paging;
import weibo4j.model.RepostTimelineIds;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;


public class MyClass {
	public int com1;
	public int com2;
	public int rep1;
	public int rep2;
	public void myclass(String id) {
		String access_token = "2.00_7C8gFOxK_cD9ec28c4cff8Y6FyB";
		//String id = "4023655136464016";
		Timeline tm = new Timeline(access_token);
		HashMap<String,Integer> one = new HashMap<String,Integer>();
		HashMap<String,Integer> two = new HashMap<String,Integer>();
		
		int comments2 = 0;	
		int reposts1 = 0;
		int reposts2 = 0;
	
		RepostTimelineIds ids = null;
		List<String> id1 = null;
		StringBuffer sb = new StringBuffer("");
		int n = 0;

		try {
			JSONArray json = tm.getStatusesCount(id);
			one.put("comments", (Integer)json.getJSONObject(0).get("comments"));
			one.put("reposts", (Integer)json.getJSONObject(0).get("reposts"));
			reposts1 = (Integer)json.getJSONObject(0).get("reposts");
			
			
		  for ( int i = 1 ; i <= 10; i++){
			ids = tm.getRepostTimelineIds(id,new Paging(i,200));
		    id1 = ids.getStatusesIds();
			for ( String dd : id1 ){
				sb.append(dd + ",");
				n++;
				if ( n == 2 ){
					sb.replace(sb.length()-1, sb.length()-1, "");
					json = tm.getStatusesCount(sb+"");
					comments2 += (Integer)json.getJSONObject(0).get("comments");
				    reposts2 += (Integer)json.getJSONObject(0).get("reposts");
				    n = 0;
				    sb.replace(0, sb.length(), "");
				}						 
			}			           
		 }
		  two.put("comments",comments2);
		  two.put("reposts",reposts2);
		
	      com1 = one.get("comments");
	      com2 = two.get("comments");
	      rep1 = one.get("reposts");
	      rep2 = two.get("reposts");
			
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
