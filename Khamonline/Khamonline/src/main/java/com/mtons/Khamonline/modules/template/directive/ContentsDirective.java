/**
 *
 */
package com.mtons.Khamonline.modules.template.directive;

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.data.view.ChannelView;
import com.mtons.Khamonline.modules.entity.Channel;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.base.utils.BeanMapUtils;
import com.mtons.Khamonline.modules.entity.TypeChannel;
import com.mtons.Khamonline.modules.service.ChannelService;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.modules.service.TypeChannelService;
import com.mtons.Khamonline.modules.template.DirectiveHandler;
import com.mtons.Khamonline.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author langhsu
 */
@Component
public class ContentsDirective extends TemplateDirective {
    @Autowired
    private PostService postService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private TypeChannelService typeChannelService;

    @Override
    public String getName() {
        return "contents";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer channelId = handler.getInteger("channelId", 0);
        String order = handler.getString("order", Consts.order.NEWEST);

        Set<Integer> excludeChannelIds = new HashSet<>();

        if (channelId <= 0) {
            List<TypeChannel> typeChannels = typeChannelService.finAll();
            List<Channel> channels = channelService.findAll(Consts.STATUS_CLOSED);
            List<ChannelView> channelViews = new ArrayList<>();
            ChannelView channelView;
            for(TypeChannel typeChannel : typeChannels) {
                channelView = ChannelView.from(typeChannel, channels.stream()
                        .filter(channel -> channel.getTypeChannelId() == typeChannel.getId()).collect(Collectors.toList()));
                if(channelView != null) {
                    channelViews.add(channelView);
                }
            }


            //
            if (!channelViews.isEmpty()) {
                channelViews.forEach((c) -> c.getChannels().forEach((c1) -> excludeChannelIds.add(c1.getId())));
            }
        }

        Pageable pageable = wrapPageable(handler, Sort.by(Sort.Direction.DESC, BeanMapUtils.postOrder(order)));
        Page<PostVO> result = postService.paging(pageable, channelId, excludeChannelIds);
        handler.put(RESULTS, result).render();
    }
}
