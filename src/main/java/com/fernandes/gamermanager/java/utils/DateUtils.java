package com.fernandes.gamermanager.java.utils;

import com.google.protobuf.Timestamp;
import proto.ReleaseDate;

import java.util.Date;

public class DateUtils {

    public static Date timestampProtoToDate(Timestamp timestamp) {
        if (timestamp == null) return null;
        return new Date(timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1_000_000);
    }
}
