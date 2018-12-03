package com.academic.as.demo.config;

public class Timing implements Comparable<Timing>{
    private int day = 0, hallIdx = 0, remainTime = 0;
    public  Timing() {
        day = 0;
        hallIdx = 0;
        remainTime = 0;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getHallIdx() {
        return hallIdx;
    }
    public void setHallIdx(int hallIdx) {
        this.hallIdx = hallIdx;
    }
    public int getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }
    @Override
    public int compareTo(Timing o) {
        if(o.remainTime > this.remainTime)
            return 1;
        else return 0;
    }


}
