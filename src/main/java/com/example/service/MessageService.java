package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {
        if(message.getMessageText() == null || message.getMessageText().length() == 0 || message.getMessageText().length() > 255) {
            throw new ClientException("Client error");
        }
        //message.getPostedBy()
        accountRepository.findById(message.getPostedBy()).orElseThrow(() -> new ClientException("Client error"));
        return  this.messageRepository.save(message);
    }

    public List<Message> retrieveAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message retrieveMessageById(Integer messageId) {
        return this.messageRepository.findById(messageId).orElse(null);
    }

    public Integer deleteMessageById(Integer messageId) {
        Integer rows = null;
        if(messageId != null){
            try {
                this.messageRepository.deleteById(messageId);
                rows = 1;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return rows;
    }
}
