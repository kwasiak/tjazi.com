package com.tjazi.chatroom.service;

import com.tjazi.chatroom.model.SingleChatroomData;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by kwasiak on 20/07/15.
 */

@Service
public class SingleChatroomDriverFactoryImpl implements SingleChatroomDriverFactory {

    @Override
    public SingleChatroomDriver createSingleChatroomDriver(String chatroomName) {

        SingleChatroomData chatroomData = new SingleChatroomData();
        chatroomData.setChatroomUuid(UUID.randomUUID());
        chatroomData.setChatroomName(chatroomName);

        return new SingleChatroomDriverImpl(chatroomData);
    }
}
