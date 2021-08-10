package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/29 10:33
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class WebUtils {

    public static <T> T  copyParamToBean(Map value, T bean) {
        try{
            System.out.println("注入之前:"+bean);

            BeanUtils.populate(bean, value);

            System.out.println("注入之后:"+bean);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return bean;
    }
}
