package io.mycat.util;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * chenjunwen
 */
public class CollectionUtil {

    /*
    在不移除现有元素的时候,只进行新增减少元素的变化
     */
    public static void safeUpdate(Map t, Map updateInfo) {
        MapDifference difference = Maps.difference(updateInfo, t);
        Map commonMap = difference.entriesInCommon();//求交集,交集为可以持续提供服务的数据源
        Map entriesOnlyOnLeft = difference.entriesOnlyOnLeft();//两个map，左边有，右边没有的entry,为需要移除的数据源
        for (Object s : entriesOnlyOnLeft.keySet()) {
            t.remove(s);
        }
        t.putAll(commonMap);
    }

    /*
在不移除现有元素的时候,只进行新增减少元素的变化
 */
    public static void safeUpdate(List t, List updateObject) {
        t.retainAll(updateObject);//保留交集
        Iterator iterator = updateObject.iterator();
        while (iterator.hasNext()) {//左边有，右边没有的entry,为需要移除的数据源
            Object next = iterator.next();
            if (!t.contains(next)) {
                t.add(next);
            }
        }
    }
}