package com.mtons.Khamonline.modules.service.impl;

import com.mtons.Khamonline.modules.entity.TypeChannel;
import com.mtons.Khamonline.modules.repository.TypeChannelRepository;
import com.mtons.Khamonline.modules.service.TypeChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TypeChannelServiceImpl implements TypeChannelService {
    @Autowired
    private TypeChannelRepository typeChannelRepository;

    @Override
    public List<TypeChannel> finAll() {
        return typeChannelRepository.findAll();
    }
}
