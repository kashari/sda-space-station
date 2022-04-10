package com.issproject.utils;

import java.util.Date;

public class Utils {
    public static Date timeStampToDate(long timeStamp){
        return new Date(timeStamp*1000);
    }
}
