package com.cathy.rewards.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constant {
    public final static String EMPTY_ID = "Customer Id is null";
    public final static String INVALID_MONTH = "Invalid month";
    public final static String INVALID_COST = "Invalid cost";
    public static final Set<String> MONTHS_SET = new HashSet<>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
}
