package Services;

import java.sql.Date;
import java.util.List;

import Entity.UserPost;

public interface IUserPostService {
	List<UserPost> paginationPage(int index, int numberOfPage);

	void insert(UserPost userPost);


	public List<UserPost> paginationPostUser(int index, int numberOfPage, String uid);

	public List<UserPost> paginationPostProfile(int index, int numberOfPage, String uid);


	// hieu-begin
	void update(UserPost userPost);

	void delete(int userPostID);

	Long countAll();

	// hieu-end

	// tuan - begin - like post
	void insertLikePost(String userID, int userPostID, Date likeTime);
	// tuan -end - like post
	UserPost findOne(int userPostID);

}
