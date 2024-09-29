package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

// 表示这个类是一个切面，用于定义切入点和通知
@Aspect
// 这是Lombok的注解，用于生成日志记录器，方便在代码中记录日志。
@Slf4j
@Component
public class AutoFillAspect {
    /**
     * 切入点
     */
    // 表示匹配com.sky.mapper包下的所有类的所有方法。
    // 1. 第一个* 表示方法的返回类型，可以是任何类型
    // 2. com.sky.mapper：这是包名，表示你想要拦截的类所在的包
    // 3. 第二个 *：表示包下的任何类。这意味着该包下的所有类都会被匹配。
    // 4. 第三个 *：表示类中的任何方法。即该类下的所有方法都会被匹配。
    // (..)：表示匹配任何数量的参数。..可以表示零个或多个参数。
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * 前置通知
     */
    //通知 是指在切入点匹配的方法执行时，你希望执行的操作。
    @Before("autoFillPointCut()")
    public void autoFillBefore(JoinPoint joinPoint) {
        log.info("====AutoFillPointCut 字段字段自动填充====");
        // 获取当前被拦截的方法上的数据库操作实体
        // 这几行代码是常见的在AOP中使用的固定写法，主要用于获取方法的签名和注解信息。
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // joinPoint是AOP中提供的一个对象，表示被拦截的目标方法的信息。
        // getSignature()方法获取这个方法的签名，签名包含了方法的名称、返回类型和参数类型等。
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = autoFill.value();

        // 获取当前被拦截的方法参数——实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        // 准备赋值数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(value == OperationType.INSERT) {
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 通过反射为对象属性赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else if(value == OperationType.UPDATE) {
            // 为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 通过反射为对象属性赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
