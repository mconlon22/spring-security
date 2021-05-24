package com.bezkoder.springjwt.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bezkoder.springjwt.models.Card;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.CardRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
	UserRepository userRepository;
  @Autowired
  PasswordEncoder encoder;
    @PostMapping(path = "/getCards") 
  @CrossOrigin

  public @ResponseBody List<Card> getCards() throws JsonProcessingException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName="";
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
    currentUserName = authentication.getName();
    }
    User user=userRepository.findByUsername(currentUserName).get();

    Long uid=user.getId();
    		

    List<Card> cards = cardRepository.findByCardUser(uid);
    for(Card card:cards){
      card.decrypt();
    }
     return cards;
    
    
    
  }
  @PostMapping(path="/addCard") // Map ONLY POST Requests
  @CrossOrigin
  public @ResponseBody String addCard(@RequestParam String cardname,
      @RequestParam String cardnum, @RequestParam String cardcvc, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date carddate) {
        // This returns a JSON or XML with the users
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh");  
        String strDate = dateFormat.format(carddate);  
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName="";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
        currentUserName = authentication.getName();
        }
        User user=userRepository.findByUsername(currentUserName).get();
    
        Long uid=user.getId();
      
    Card card = new Card();
    card.setCardcvc(cardcvc);
    card.setCardnum(cardnum);
    card.setCardname(cardname);
    card.setCarddate(strDate);
    card.setUser(userRepository.findById((long)uid).get());
        card.encrypt();
    cardRepository.save(card);

 return "success";
    
  }

  @PostMapping(path = "/getCard") 
  @CrossOrigin

  public @ResponseBody Card getCard(@RequestParam int cardId) throws JsonProcessingException {
    Long uid=(long)cardId;
    Card card = cardRepository.findById(uid).get();
    card.decrypt();
     return card;
    
    
    
  }
  @PostMapping(path = "/removeCard") 
  @CrossOrigin

  public @ResponseBody String removeCard(@RequestParam int cardId) throws JsonProcessingException {
    Long uid=(long)cardId;
    cardRepository.deleteById( uid);
   
    return "success";
    
  }
  @PostMapping(path="/editCard") // Map ONLY POST Requests
  @CrossOrigin
@ResponseBody String editCard(@RequestParam int cardId, @RequestParam String cardname,@RequestParam String cardnum, @RequestParam String cardcvc, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date carddate) {
    // This returns a JSON or XML with the users
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh");  
    String strDate = dateFormat.format(carddate);  
System.out.println("card");
  
Card card =cardRepository.findById((long)cardId).get();
card.setCardcvc(cardcvc);
card.setCardnum(cardnum);
card.setCardname(cardname);
card.setCarddate(strDate);

cardRepository.save(card);

return "success";
}
}
