package com.behere.testDemo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Behere
 */
public class Test {

    static class B implements Serializable {
        public Class[] clazz;
        public Map aInstance;
    }

    public static void main(String[] args) {
        B b = new B();
        b.clazz = new Class[]{String.class};
        b.aInstance = new HashMap();
        b.aInstance.put("test", "test");
        String s = JSON.toJSONString(b);
        B a1 = JSON.parseObject(s, B.class);
    }

}
