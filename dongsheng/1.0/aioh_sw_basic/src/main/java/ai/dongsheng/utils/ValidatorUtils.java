package ai.dongsheng.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @program: dongsheng
 * @description: JSR303标准参数校验工具类
 * @author: MichelleJou
 * @create: 2019-07-25 15:13
 **/
public class ValidatorUtils {
    /*
     * description  JSR303参数校验不支持分组
     * date         2019/7/25 15:15
     * @author      MichelleJou
     * @param       t
     * @return
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    public static <T> List<Map<String, String>> validate(T t) {
        //定义返回错误List
        List<Map<String, String>> errList = new ArrayList<Map<String, String>>();

        Map<String, String> errorMap;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> errorSet = validator.validate(t);

        for (ConstraintViolation<T> c : errorSet) {
            errorMap = new HashMap<>();
            errorMap.put("field", c.getPropertyPath().toString()); //获取发生错误的字典名称
            errorMap.put("msg", c.getMessage()); //获取校验信息
            errList.add(errorMap);
        }

        return errList;
    }


    /*
     * description  JSR303参数校验，可以支持分组约束校验
     * date         2019/7/25 15:14
     * @author      MichelleJou
     * @param       t
     * @param       group
     * @return
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    public static <T> List<Map<String, String>> validates(T t, Class group) {
        //定义返回错误List
        List<Map<String, String>> errList = new ArrayList<Map<String, String>>();

        Map<String, String> errorMap;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> errorSet = validator.validate(t, group);

        for (ConstraintViolation<T> c : errorSet) {
            errorMap = new HashMap<>();
            errorMap.put("field", c.getPropertyPath().toString()); //获取发生错误的字典名称
            errorMap.put("msg", c.getMessage()); //获取校验信息
            errList.add(errorMap);
        }

        return errList;
    }
}
