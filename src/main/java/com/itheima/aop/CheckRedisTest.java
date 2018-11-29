package com.itheima.aop;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.anno.CheckRedis;
import com.itheima.domain.User;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;


public class CheckRedisTest {

    @Autowired
    private Jedis jedis;


    public Object aroundRedis(ProceedingJoinPoint pjp)  {

        //获取方法名
        String methodName = pjp.getSignature().getName();
        //获取参数数组 及其字节码数组对象
        Object[] args = pjp.getArgs();
        Class[] argsClass = new Class[args.length];
        //定义返回结果
        Object proceed = null;
        ObjectMapper mapper = new ObjectMapper();
        //获取目标类的对象的字节码对象
        Class<?> tgClass = pjp.getTarget().getClass();
        try {
            //获取方法对象
            Method method = tgClass.getMethod(methodName, argsClass);
            //被注解
            if (method.isAnnotationPresent(CheckRedis.class)){
                //执行方法
                String rtValue = jedis.get(tgClass.getCanonicalName()+"."+methodName);
                if (rtValue == null||"".equals(rtValue)||rtValue.length() == 0){
                    //没有缓存 先执行方法查询
                    System.out.println("-----没有缓存 执行数据库-----");
                    proceed = pjp.proceed();
                    //模拟业务时间
                    sleepTime();
                    // 查询结果是对象集合 转json
                    String Value = mapper.writeValueAsString(proceed);
                    //存入redis 并返回
                    jedis.set(tgClass.getCanonicalName()+"."+methodName,Value);
                    return proceed;
                }else {

                    System.out.println("----执行缓存----");
                    // 有缓存 将redis中的json字符串转为对象数组
                    proceed = mapper.readValue(rtValue, new TypeReference<List<User>>(){});
                    return proceed;
                }
            }else {
                //没有注解直接执行方法
                System.out.println("----没有注解----");
                return pjp.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    public void sleepTime(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
