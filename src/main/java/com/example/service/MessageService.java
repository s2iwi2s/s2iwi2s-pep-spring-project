package com.example.service;

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
}
