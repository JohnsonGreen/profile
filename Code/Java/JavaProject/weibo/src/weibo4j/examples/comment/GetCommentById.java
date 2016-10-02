package weibo4j.examples.comment;


import weibo4j.Comments;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

public class GetCommentById {
	String access_token = "2.00_7C8gFOxK_cD9ec28c4cff8Y6FyB";
	public String text;
	public int number;
	public void getcomment(String a) {
		Comments cm = new Comments(access_token);
		try {
			CommentWapper comment = cm.getCommentById(a);
			text = comment.toString();
			number = comment.numbers();
			//for(int i = 0;i < text.length;i++){
			//System.out.println(text);
			//}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
