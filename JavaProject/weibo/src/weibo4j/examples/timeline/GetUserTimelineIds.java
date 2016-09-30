package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.UserTimelineIds;
import weibo4j.model.WeiboException;

public class GetUserTimelineIds {
	
	public static void main(String[] args) {
		String access_token = "2.00_7C8gFOxK_cD9ec28c4cff8Y6FyB";
		String uid = "5207714447";
		Timeline tm = new Timeline(access_token);
		try {
			UserTimelineIds ids = tm.getUserTimelineIdsByUid(uid);
			Log.logInfo(ids.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
