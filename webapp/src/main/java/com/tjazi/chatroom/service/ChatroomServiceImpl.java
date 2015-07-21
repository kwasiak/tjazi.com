package com.tjazi.chatroom.service;

import com.tjazi.chatroom.model.SingleChatroomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by kwasiak on 19/07/15.
 */

@Service
public class ChatroomServiceImpl implements ChatroomService {

    private List<SingleChatroomDriver> chatroomData = new ArrayList<>();

    @Autowired
    private SingleChatroomDriverFactory singleChatroomDriverFactory;

    @Override
    public boolean isChatroomExist(String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {
            throw new IllegalArgumentException("chatroomName is null or empty");
        }

        return chatroomData.stream()
                .anyMatch(
                        data -> data.getChatroomName()
                                .equalsIgnoreCase(chatroomName));
    }

    @Override
    public SingleChatroomDriver createNewChatroom(String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {
            throw new IllegalArgumentException("chatroomName is null or empty");
        }

        if (this.isChatroomExist(chatroomName)) {
            throw new IllegalArgumentException("There's already chatroom with name: " + chatroomName);
        }

        SingleChatroomDriver chatroomDriver = singleChatroomDriverFactory.createSingleChatroomDriver(chatroomName);

        chatroomData.add(chatroomDriver);

        return chatroomDriver;
    }

    public SingleChatroomDriver findChatroomByUuid(UUID chatroomUuid) {

        if (chatroomUuid == null) {
            throw new IllegalArgumentException("chatroomUiid is null");
        }

        Optional<SingleChatroomDriver> matchingElement =
                chatroomData.stream()
                .filter(element -> element.getChatroomUuid().equals(chatroomUuid))
                .findFirst();

        // return result of the search or NULL if there's nothing to return
        return matchingElement.orElse(null);
    }

}
