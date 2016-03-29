package com.grgbanking.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：qywx_ms
 * 
 * 类描述：缓存工具类 类名称：com.grgbanking.common.utils.EhcacheUtils 创建人：TXH 创建时间：2015-7-20
 * 上午10:44:58 修改人： 修改时间：2015-7-20 上午10:44:58 修改备注：
 * 
 * @version V1.0
 */
public class EhcacheUtils {

    public static CacheManager manager = CacheManager.create();

    /**
     * 
     * @Title: get
     * @Description: TODO(获取)
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return element.getObjectValue();
            }
        }
        return null;
    }

    /**
     * 
     * @Title: put
     * @Description: TODO(保存)
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, Object key, Object value) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            cache.put(new Element(key, value));
        }
    }

    /**
     * 
     * @Title: remove
     * @Description: TODO(移除)
     * @param cacheName
     * @param key
     * @return
     */
    public static boolean remove(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            return cache.remove(key);
        }
        return false;
    }

    /**
     * 
     * @Title: update
     * @Description: TODO(更新)
     * @param cacheName
     * @param key
     * @param value
     * @return
     */
    public static boolean update(String cacheName, Object key, Object value) {
        remove(cacheName, key);
        put(cacheName, key, value);
        return true;
    }

    public static void main(String[] args) {
        String key = "key";
        String value = "hello";
        EhcacheUtils.put("session", key, value);
        System.out.println(EhcacheUtils.get("session", key));
    }

}