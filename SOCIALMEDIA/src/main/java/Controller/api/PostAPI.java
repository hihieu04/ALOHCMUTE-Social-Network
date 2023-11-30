package Controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entity.User;
import Entity.UserPost;
import Model.UserPostModel;
import Services.IUserPostService;
import Services.IUserService;
import Services.UserPostServiceImpl;
import Services.UserServiceImpl;
@WebServlet(urlPatterns = {"/posts"})

public class PostAPI extends HttpServlet{
	IUserService userService = new UserServiceImpl();
	IUserPostService userPostService = new UserPostServiceImpl();
	// CRUD
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		UserPostModel newPost = gson.fromJson(req.getReader(),UserPostModel.class);
		System.out.println("userpost da post: "+ newPost);
		UserPost userPost = new UserPost();
		userPost.setUserPostText(newPost.getText());
		userPost.setUserPostCreateTime(newPost.getCreateTime());
		User user = new User();
		user.setUserID(newPost.getUserid());
		userPost.setUser(user);
		// còn thiếu dữ liệu hình ảnh 
		userPostService.insert(userPost);
	}
	// các method
	public void postLoadAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String amount = req.getParameter("exits");
		int imount = Integer.parseInt(amount);
		List<UserPost> listPost = userPostService.paginationPage(imount, 6);
		List<UserPostModel> listPostModel = new ArrayList<UserPostModel>();
		for (UserPost post : listPost) {
			String username = post.getUser().getLastName() + ' ' + post.getUser().getMidName() + ' '
					+ post.getUser().getFirstName();
			String userid = post.getUser().getUserID();
			int postid = post.getUserPostID();
			String text = post.getUserPostText();
			Date createTime = post.getUserPostCreateTime();
			String img = post.getUserPostImg();
			UserPostModel postModel = new UserPostModel(username, userid, postid, text, createTime, img);
			listPostModel.add(postModel);
		}

		Gson gson = new Gson();
		String listPostJson = gson.toJson(listPostModel);
		System.out.println("list post lay duoc ; " + listPostModel);
		PrintWriter out = resp.getWriter();
		out.println(listPostJson);
		out.close();
	}
}