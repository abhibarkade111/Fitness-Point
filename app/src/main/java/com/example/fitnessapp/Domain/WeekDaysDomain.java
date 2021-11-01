package com.example.fitnessapp.Domain;

public class WeekDaysDomain {
    private String dayText;

    public WeekDaysDomain(String dayText) {
        this.dayText = dayText;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }
}
