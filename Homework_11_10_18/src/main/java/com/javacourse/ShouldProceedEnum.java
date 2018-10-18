package com.javacourse;

public enum ShouldProceedEnum {
    YES,
    NO,
    UNKNOWN;

    public static ShouldProceedEnum parseUserChoice(String choice){
        if(choice.equals("YES") || choice.equals("yes") || choice.equals("Yes"))
            return  YES;
        else if(choice.equals("NO") || choice.equals("no") || choice.equals("No"))
            return NO;
        else return UNKNOWN;
    }
}
