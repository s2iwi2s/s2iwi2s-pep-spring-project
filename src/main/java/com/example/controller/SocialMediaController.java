package com.example.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientException;
import com.example.exception.ConflictException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("")
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) throws ClientException, ConflictException {
        account = this.accountService.register(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws LoginException {
        account = this.accountService.login(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws LoginException {
        message = this.messageService.createMessage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages() throws LoginException {
        List<Message> messages = this.messageService.retrieveAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> retrieveMessagesById(@PathVariable Integer messageId) throws LoginException {
        Message message = this.messageService.retrieveMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
