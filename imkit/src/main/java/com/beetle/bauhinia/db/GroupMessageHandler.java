package com.beetle.bauhinia.db;

import com.beetle.im.IMMessage;

import java.util.Date;

/**
 * Created by houxh on 15/3/21.
 */
public class GroupMessageHandler implements com.beetle.im.GroupMessageHandler {

    private static GroupMessageHandler instance = new GroupMessageHandler();

    public static GroupMessageHandler getInstance() {
        return instance;
    }


    public static int now() {
        Date date = new Date();
        long t = date.getTime();
        return (int)(t/1000);
    }

    public boolean handleMessage(IMMessage msg) {
        GroupMessageDB db = GroupMessageDB.getInstance();
        IMessage imsg = new IMessage();
        imsg.sender = msg.sender;
        imsg.receiver = msg.receiver;
        imsg.timestamp = now();
        imsg.setContent(msg.content);
        return db.insertMessage(imsg, imsg.receiver);
    }
    public boolean handleMessageACK(int msgLocalID, long gid) {
        GroupMessageDB db = GroupMessageDB.getInstance();
        return db.acknowledgeMessage(msgLocalID, gid);
    }

    public boolean handleMessageFailure(int msgLocalID, long gid) {
        GroupMessageDB db = GroupMessageDB.getInstance();
        return db.markMessageFailure(msgLocalID, gid);
    }

    public boolean handleGroupNotification(String notification) {
        GroupMessageDB db = GroupMessageDB.getInstance();
        IMessage.GroupNotification groupNotification = IMessage.newGroupNotification(notification);
        IMessage imsg = new IMessage();
        imsg.sender = 0;
        imsg.receiver = groupNotification.groupID;
        imsg.timestamp = now();
        imsg.setContent(groupNotification);
        return db.insertMessage(imsg, groupNotification.groupID);
    }

}
