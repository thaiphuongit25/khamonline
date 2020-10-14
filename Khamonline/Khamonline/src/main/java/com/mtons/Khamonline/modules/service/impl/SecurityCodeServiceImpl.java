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

import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.base.lang.EntityStatus;
import com.mtons.Khamonline.base.lang.MtonsException;
import com.mtons.Khamonline.modules.repository.SecurityCodeRepository;
import com.mtons.Khamonline.modules.service.SecurityCodeService;
import com.mtons.Khamonline.modules.entity.SecurityCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author langhsu on 2015/8/14.
 */
@Service
public class SecurityCodeServiceImpl implements SecurityCodeService {
    @Autowired
    private SecurityCodeRepository securityCodeRepository;

    // 验证码存活时间 单位：分钟
    private int survivalTime = 30;

    @Override
    @Transactional
    public String generateCode(String key, int type, String target) {
        SecurityCode po = securityCodeRepository.findByKey(key);

        String code = RandomStringUtils.randomNumeric(6);
        Date now = new Date();

        if (po == null) {
            po = new SecurityCode();
            po.setKey(key);
            po.setCreated(now);
            po.setExpired(DateUtils.addMinutes(now, survivalTime));
            po.setCode(code);
            po.setType(type);
            po.setTarget(target);
        } else {

            long interval = ( now.getTime() - po.getCreated().getTime() ) / 1000;

            if (interval <= 60) {
                throw new MtonsException("Khoảng thời gian gửi không được dưới 1 phút");
            }

            // 把 验证位 置0
            po.setStatus(EntityStatus.ENABLED);
            po.setCreated(now);
            po.setExpired(DateUtils.addMinutes(now, survivalTime));
            po.setCode(code);
            po.setType(type);
            po.setTarget(target);
        }

        securityCodeRepository.save(po);

        return code;
    }

    @Override
    @Transactional
    public boolean verify(String key, int type, String code) {
        Assert.hasLength(code, "mã xác minh phải được điền");
        SecurityCode po = securityCodeRepository.findByKeyAndType(key, type);
        Assert.notNull(po, "Bạn chưa thực hiện xác minh loại");

        Date now = new Date();

        Assert.state(now.getTime() <= po.getExpired().getTime(), "Mã xác minh đã hết hạn");
        Assert.isTrue(po.getType() == type, "Lỗi loại mã xác minh");
        Assert.isTrue(po.getStatus() == Consts.CODE_STATUS_INIT, "Mã xác minh đã được sử dụng");
        Assert.state(code.equals(po.getCode()), "Mã xác minh không chính xác");

        po.setStatus(Consts.CODE_STATUS_CERTIFIED);
        securityCodeRepository.save(po);
        return true;
    }

}
