package com.mtons.Khamonline.modules.data.view;

import com.mtons.Khamonline.modules.entity.Channel;
import com.mtons.Khamonline.modules.entity.TypeChannel;

import java.util.List;

public class ChannelView {
    private int typeChannelId;
    private String name;
    private List<Channel> channels;

    public int getTypeChannelId() {
        return typeChannelId;
    }

    public void setTypeChannelId(int typeChannelId) {
        this.typeChannelId = typeChannelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public static ChannelView from(TypeChannel typeChannel, List<Channel> channels) {
        if (typeChannel == null || channels == null || channels.isEmpty()) {
            return null;
        }
        ChannelView channelView = new ChannelView();
        channelView.setTypeChannelId(typeChannel.getId());
        channelView.setChannels(channels);
        channelView.setName(typeChannel.getName());
        return channelView;
    }
}
