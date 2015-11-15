package gson学习与反射.entity;
import java.util.ArrayList;
import java.util.List;
public class Top_data {
	private String summary;
	private List<Top_data_follower> follower=new ArrayList<Top_data_follower>();
	private String status;
	private String update_timestamp;
	private String main_pic;
	private String follow_cnt;
	private String back_pic;
	private String followed;
	private String id;
	private String title;
	private String main_pic_sub;
	private String like_cnt;
	private String comment_cnt;
	private String name;
	private String user_id;
	private String content_type;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Top_data_follower> getFollower() {
		return follower;
	}

	public void setFollower(List<Top_data_follower> follower) {
		this.follower = follower;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(String update_timestamp) {
		this.update_timestamp = update_timestamp;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	public String getFollow_cnt() {
		return follow_cnt;
	}

	public void setFollow_cnt(String follow_cnt) {
		this.follow_cnt = follow_cnt;
	}

	public String getBack_pic() {
		return back_pic;
	}

	public void setBack_pic(String back_pic) {
		this.back_pic = back_pic;
	}

	public String getFollowed() {
		return followed;
	}

	public void setFollowed(String followed) {
		this.followed = followed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMain_pic_sub() {
		return main_pic_sub;
	}

	public void setMain_pic_sub(String main_pic_sub) {
		this.main_pic_sub = main_pic_sub;
	}

	public String getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(String like_cnt) {
		this.like_cnt = like_cnt;
	}

	public String getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(String comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

}