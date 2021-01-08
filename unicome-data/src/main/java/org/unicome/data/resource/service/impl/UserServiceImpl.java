package org.unicome.data.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicome.data.domain.mysql.user.User;
import org.unicome.data.domain.mysql.user.UserDao;
import org.unicome.data.resource.constant.PageConstants;
import org.unicome.data.resource.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    public List<User> listAll(int pageIndex, int pageSize) {
        List<User> users = userDao.findAll(pageIndex > 0 ? pageIndex - 1 : PageConstants.DEFAULT_PAGE_INDEX,
                pageSize > 0 ? pageSize : PageConstants.DEFAULT_PAGE_SIZE);
        return users;
    }

    @Override
    public List<User> listAllByGroupId(int groupId) {
        List<User> users = userDao.findAllByGroupId(groupId);
        return users;
    }

    @Override
    public User getByUsername(String username) {
        User user = userDao.findByColumn("username", username);
        return user;
    }

    @Override
    public User getByMobile(String mobile) {
        User user = userDao.findByColumn("mobile", mobile);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.findByColumn("email", email);
        return user;
    }
}
