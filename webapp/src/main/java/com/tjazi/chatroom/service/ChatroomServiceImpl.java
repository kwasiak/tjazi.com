package com.tjazi.chatroom.service;

import com.tjazi.chatroom.model.SingleChatroomData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 19/07/15.
 */

@Service
public class ChatroomServiceImpl implements ChatroomService {

    private List<SingleChatroomData> chatroomData = new ArrayList<>();

    @Override
    public boolean isChatroomExist(String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {
            throw new IllegalArgumentException("chatroomName is null or empty");
        }

        return chatroomData.stream()
                .anyMatch(data -> data.getChatroomName().toUpperCase().equals(chatroomName.toUpperCase()));
    }

    @Override
    public UUID creteNewChatroom(String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {
            throw new IllegalArgumentException("chatroomName is null or empty");
        }

        if (this.isChatroomExist(chatroomName)) {
            throw new IllegalArgumentException("There's already chatroom with name: " + chatroomName);
        }

        SingleChatroomData chatroomData = new SingleChatroomData();
        chatroomData.setChatroomName(chatroomName);

        UUID chatroomUuid = UUID.randomUUID();
        chatroomData.setChatroomUuid(chatroomUuid);

        return chatroomUuid;
    }

    @Override
    public boolean isChatroomHaveUser(String chatroomName, String userName) {
        return false;
    }

    @Override
    public void addUserToChatroom(String userName) {

    }

    private SingleChatroomData findChatroomByName(String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {
            throw new IllegalArgumentException("chatroomName is null or empty");
        }

        chatroomData
    }
}
