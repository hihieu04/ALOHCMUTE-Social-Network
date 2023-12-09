package Dao;

import java.util.List;

import Entity.User;
import Entity.UserPost;

public interface IUserPostDao {
	List<UserPost> paginationPage(int index, int numberOfPage);
	//hieu-begin
	void update(UserPost userPost);
	void delete(int userPostID);
	Long countAll();//hieu them
	//hieu-end
	
}
