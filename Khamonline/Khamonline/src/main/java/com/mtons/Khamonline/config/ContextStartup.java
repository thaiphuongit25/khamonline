package com.mtons.Khamonline.config;

import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.view.ChannelView;
import com.mtons.Khamonline.modules.entity.Channel;
import com.mtons.Khamonline.modules.entity.Options;
import com.mtons.Khamonline.modules.entity.TypeChannel;
import com.mtons.Khamonline.modules.service.ChannelService;
import com.mtons.Khamonline.modules.service.MailService;
import com.mtons.Khamonline.modules.service.OptionsService;
import com.mtons.Khamonline.modules.service.TypeChannelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @since 3.0
 */
@Slf4j
@Order(2)
@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SiteOptions siteOptions;
    @Autowired
    private TypeChannelService typeChannelService;

    private ServletContext servletContext;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("initialization ...");
        reloadOptions(true);
        resetChannels();
        log.info("OK, completed");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void reloadOptions(boolean startup) {
        List<Options> options = optionsService.findAll();

        log.info("find options ({})...", options.size());

        if (startup && CollectionUtils.isEmpty(options)) {
            try {
                log.info("init options...");
                Resource resource = new ClassPathResource("/scripts/schema.sql");
                optionsService.initSettings(resource);
                options = optionsService.findAll();
            } catch (Exception e) {
                log.error("------------------------------------------------------------");
                log.error("-          ERROR: The database is not initialized          -");
                log.error("------------------------------------------------------------");
                log.error(e.getMessage(), e);
                System.exit(1);
            }
        }

        Map<String, String> map = siteOptions.getOptions();
        options.forEach(opt -> {
            if (StringUtils.isNoneBlank(opt.getKey(), opt.getValue())) {
                map.put(opt.getKey(), opt.getValue());
            }
        });
        servletContext.setAttribute("options", map);
        servletContext.setAttribute("site", siteOptions);
        mailService.config();

        System.setProperty("site.location", siteOptions.getLocation());
    }

    public void resetChannels() {
        List<TypeChannel> typeChannels = typeChannelService.finAll();
        List<Channel> channels = channelService.findAll(Consts.STATUS_NORMAL);
        List<ChannelView> channelViews = new ArrayList<>();
        ChannelView channelView;
        for(TypeChannel typeChannel : typeChannels) {
            channelView = ChannelView.from(typeChannel, channels.stream()
                    .filter(channel -> channel.getTypeChannelId() == typeChannel.getId()).collect(Collectors.toList()));
            if(channelView != null) {
                channelViews.add(channelView);
            }
        }

        servletContext.setAttribute("typeChannel", channelViews);
    }

}
