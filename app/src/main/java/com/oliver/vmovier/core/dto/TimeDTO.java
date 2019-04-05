package com.oliver.vmovier.core.dto;

import java.util.Locale;

import com.alibaba.fastjson.annotation.JSONField;

public class TimeDTO {

    @JSONField(name = "year")
    private String mYear;

    @JSONField(name = "month")
    private String mMonth;

    @JSONField(name = "day")
    private String mDay;

    @JSONField(name = "week")
    private String mWeek;

    @JSONField(name = "year")
    public String getYear() {
        return mYear;
    }

    @JSONField(name = "year")
    public void setYear(String year) {
        mYear = year;
    }

    @JSONField(name = "month")
    public String getMonth() {
        return mMonth;
    }

    @JSONField(name = "month")
    public void setMonth(String month) {
        mMonth = month;
    }

    @JSONField(name = "day")
    public String getDay() {
        return mDay;
    }

    @JSONField(name = "day")
    public void setDay(String day) {
        mDay = day;
    }

    @JSONField(name = "week")
    public String getWeek() {
        return mWeek;
    }

    @JSONField(name = "week")
    public void setWeek(String week) {
        mWeek = week;
    }

    @Override
    public String toString() {
        return String.format(Locale.CHINA, "{TimeDTO: year = %s, month = %s, day = %s, week = %s}",
            mYear, mMonth, mDay, mWeek);
    }
}
