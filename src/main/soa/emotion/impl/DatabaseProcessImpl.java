package emotion.impl;

import emotion.dao.PostDao;
import emotion.dao.UserDao;
import emotion.service.DatabaseProcess;
import emotion.service.Post;
import emotion.service.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by lifengshuang on 21/04/2017.
 */

@WebService(endpointInterface = "emotion.service.DatabaseProcess", serviceName = "DatabaseProcess")
public class DatabaseProcessImpl implements DatabaseProcess {
    private UserDao userDao;
    private PostDao postDao;

    @WebMethod(exclude = true)
    public PostDao getPostDao() {
        return postDao;
    }

    @WebMethod(exclude = true)
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @WebMethod(exclude = true)
    public UserDao getUserDao() {
        return userDao;
    }

    @WebMethod(exclude = true)
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int createUser(String name, String email, String password) {
        UserImpl user = new UserImpl(email, password, name);
        return userDao.save(user);
    }

    @Override
    public User getUser(String email) {
        List<User> result = userDao.get(email);
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public int createPost(String text, float longtitude, float latitude, int userID) {
        PostImpl post = new PostImpl(text, longtitude, latitude, userID);
        return postDao.save(post);
    }

    @Override
    public List<Post> getSurroundingPost(float longtitude, float latitude, float range) {
        return postDao.getSurroundingPosts(longtitude, latitude, range);
    }

    @Override
    public List<Post> getMyOwnPost(int userID) {
        return postDao.getMyOwnPosts(userID);
    }

}
