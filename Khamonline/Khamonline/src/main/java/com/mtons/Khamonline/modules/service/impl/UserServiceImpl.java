/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.modules.service.impl;

import com.mtons.Khamonline.modules.data.BadgesCount;
import com.mtons.Khamonline.base.lang.EntityStatus;
import com.mtons.Khamonline.base.lang.MtonsException;
import com.mtons.Khamonline.base.utils.MD5;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.modules.data.UserVO;
import com.mtons.Khamonline.modules.entity.User;
import com.mtons.Khamonline.modules.repository.RoleRepository;
import com.mtons.Khamonline.modules.repository.UserRepository;
import com.mtons.Khamonline.modules.service.MessageService;
import com.mtons.Khamonline.modules.service.UserService;
import com.mtons.Khamonline.base.utils.BeanMapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageService messageService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<UserVO> paging(Pageable pageable, String name) {
        Page<User> page = userRepository.findAll((root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (StringUtils.isNoneBlank(name)) {
                predicate.getExpressions().add(
                        builder.like(root.get("name"), "%" + name + "%"));
            }

            query.orderBy(builder.desc(root.get("id")));
            return predicate;
        }, pageable);

        List<UserVO> rets = new ArrayList<>();
        page.getContent().forEach(n -> rets.add(BeanMapUtils.copy(n)));
        return new PageImpl<>(rets, pageable, page.getTotalElements());
    }

    @Override
    public Map<Long, UserVO> findMapByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<User> list = userRepository.findAllById(ids);
        Map<Long, UserVO> ret = new HashMap<>();

        list.forEach(po -> ret.put(po.getId(), BeanMapUtils.copy(po)));
        return ret;
    }

    @Override
    @Transactional
    public AccountProfile login(String username, String password) {
        User po = userRepository.findByUsername(username);

        if (null == po) {
            return null;
        }


        Assert.state(StringUtils.equals(po.getPassword(), password), "sai mật khẩu");

        po.setLastLogin(Calendar.getInstance().getTime());
        userRepository.save(po);
        AccountProfile u = BeanMapUtils.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);
        return u;
    }

    @Override
    @Transactional
    public AccountProfile findProfile(Long id) {
        User po = userRepository.findById(id).orElse(null);

        Assert.notNull(po, "Tài khoản không tồn tại");

        po.setLastLogin(Calendar.getInstance().getTime());

        AccountProfile u = BeanMapUtils.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);

        return u;
    }

    @Override
    @Transactional
    public UserVO register(UserVO user) {
        Assert.notNull(user, "Người dùng không được rỗng!");

        Assert.hasLength(user.getUsername(), "Tên ngươi dung không được để trông!");
        Assert.hasLength(user.getPassword(), "mật khẩu không được để trống!");

        User check = userRepository.findByUsername(user.getUsername());

        Assert.isNull(check, "tên này đã có người dùng!");

        if (StringUtils.isNotBlank(user.getEmail())) {
            User emailCheck = userRepository.findByEmail(user.getEmail());
            Assert.isNull(emailCheck, "Email đã tồn tại!");
        }

        User po = new User();

        BeanUtils.copyProperties(user, po);

        if (StringUtils.isBlank(po.getName())) {
            po.setName(user.getUsername());
        }

        Date now = Calendar.getInstance().getTime();
        po.setPassword(MD5.md5(user.getPassword()));
        po.setStatus(EntityStatus.ENABLED);
        po.setCreated(now);

        userRepository.save(po);

        return BeanMapUtils.copy(po);
    }

    @Override
    @Transactional
    public AccountProfile update(UserVO user) {
        User po = userRepository.findById(user.getId()).get();
        po.setName(user.getName());
        po.setSignature(user.getSignature());
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    @Transactional
    public AccountProfile updateEmail(long id, String email) {
        User po = userRepository.findById(id).get();

        if (email.equals(po.getEmail())) {
            throw new MtonsException("Địa chỉ email không được thay đổi");
        }

        User check = userRepository.findByEmail(email);

        if (check != null && check.getId() != po.getId()) {
            throw new MtonsException("Địa chỉ email đã được sử dụng");
        }
        po.setEmail(email);
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public UserVO get(long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            return BeanMapUtils.copy(optional.get());
        }
        return null;
    }

    @Override
    public UserVO getByUsername(String username) {
        return BeanMapUtils.copy(userRepository.findByUsername(username));
    }

    @Override
    public UserVO getByEmail(String email) {
        return BeanMapUtils.copy(userRepository.findByEmail(email));
    }

    @Override
    @Transactional
    public AccountProfile updateAvatar(long id, String path) {
        User po = userRepository.findById(id).get();
        po.setAvatar(path);
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String newPassword) {
        User po = userRepository.findById(id).get();

        Assert.hasLength(newPassword, "mật khẩu không được để trống!");

        po.setPassword(MD5.md5(newPassword));
        userRepository.save(po);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User po = userRepository.findById(id).get();

        Assert.hasLength(newPassword, "mật khẩu không được để trống!");

        Assert.isTrue(MD5.md5(oldPassword).equals(po.getPassword()), "Mật khẩu hiện tại không chính xác");
        po.setPassword(MD5.md5(newPassword));
        userRepository.save(po);
    }

    @Override
    @Transactional
    public void updateStatus(long id, int status) {
        User po = userRepository.findById(id).get();

        po.setStatus(status);
        userRepository.save(po);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

}
