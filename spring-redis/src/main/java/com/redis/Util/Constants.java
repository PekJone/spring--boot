package com.redis.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-18  9:09
 */
public class Constants {
    public static final String JHS_KEY="JHS_KEY";

    public static final Map<Long ,Map<Integer,Integer>> PV_MAP = new ConcurrentHashMap<>();

    public static final String CACHE_PV_LIST = "pv:list";

    public static final String CACHE_ARTICLE ="article:";
}
