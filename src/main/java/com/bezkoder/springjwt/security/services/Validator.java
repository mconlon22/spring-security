package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Card;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  


@Component
public class Validator {
    public String checkPassword(String str) {
        if(str.length()<6||str.length()>39){
            return "password must be between 6 and 40 characters";
        }
        System.out.println(str.length());
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return "valid";
        }
            
                return "Password must contain at least one capital letter one number and one lower case letter";
            
    }
    public String checkCard(Card card) {
        Date cardDate=null;
        try {
            cardDate=new SimpleDateFormat("yyyy-mm-dd hh").parse(card.getCarddate());
        } catch (ParseException e) {
            return "Invalid Date";
        }  
        if(card.getCardcvc().length()!=3){
            return "invalid Cvv";
        }
        if(card.getCardnum().length()!=16){
            return "invalid Card Number";
        }
        if(cardDate.after( new Date())){
            return "Invalid Card Date";
        }
        return "valid";
    }
    public boolean checkEmail(String email){
        System.out.println("Email: " + email);
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        System.out.println("Matches: " + m.matches());
        return !m.matches();
         
    }
}
