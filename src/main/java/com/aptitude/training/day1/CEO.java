package com.aptitude.training.day1;

public class CEO extends Manager {
    private String nameInt;

    public CEO(String name){
        nameInt = name;
    }

    private void sayHello(){
        System.out.println("Said hello to " + nameInt);
    }

    private void sayGoodBay(String bye)
    {
        System.out.println(bye);
    }

    public void sayGoodMorning(){}
}
