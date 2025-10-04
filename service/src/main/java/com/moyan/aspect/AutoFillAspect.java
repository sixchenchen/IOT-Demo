package com.moyan.aspect;

import com.moyan.annotation.AutoFill;
import com.moyan.constant.AutoFillConstant;
import com.moyan.context.BaseContext;
import com.moyan.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @program: IOT-Demo
 * @ClassName AutoFillAspect
 * @description: 自定义切面，实现公共字段自动填充处理
 * @author: chen
 * @create: 2025-10-03 15:21
 **/

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点:对加了AutoFill的注解的方法进行切入
     */
    @Pointcut("@annotation(com.moyan.annotation.AutoFill)")
    public void autoFilePointCut() {
    }

    @Before("autoFilePointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 获取当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();
        //获取到单前被拦截的方法的参数--实体类
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];
        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        if (currentId == null){
            currentId = 0L;
        }
        //根据当前不同的操作类型，为对应的属性通过反射来赋值
        if (operationType == OperationType.INSERT) {//新增
            // 为4个公共字段填充数据
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateBy = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_BY, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateBy = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_BY, Long.class);
                //通过反射来为对象属性赋值
                setCreateTime.invoke(entity, now);
                setCreateBy.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateBy.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (operationType == OperationType.UPDATE) {//修改
            // 为2个公共字段填充数据
            try {
                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
                entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_BY, Long.class).invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
