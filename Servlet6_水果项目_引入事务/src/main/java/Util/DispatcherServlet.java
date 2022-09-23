package Util;

import io.BeanFactory;
import io.ClassPathXmlApplicationContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

// 中央控制器
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        // 调用父类的初始化方法
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        // 假设url是：  http://localhost:8080/hello.do
        // 那么servletPath是：    /hello.do
        // 我的思路是：每次请求地址后面拼接Controller（控制器）
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController

        // -------------------------------------------------步骤一：解析路由---------------------------------------------
        // 获得请求servlet服务的路径（/xxx.do）
        String servletPath = request.getServletPath();
        // 截取/，剩下xxx.do
        servletPath = servletPath.substring(1);
        // 获取.do的下标，lastIndexOf()：获取下标
        int lastDotIndex = servletPath.lastIndexOf(".do");
        // 获取到xxx，substring()：获取指定下标的字符
        servletPath = servletPath.substring(0, lastDotIndex);

        // -------------------------------------------------步骤三：根据反射调用对应的控制器---------------------------------------------
        // 根据路由名称（对应xml中的id），获取对应类的实例。即在Map集合中，根据key获取value
        Object controllerBeanObj = beanFactory.getBean(servletPath);


        //  获取前端传递的operate（用于区分方法）
        String operate = request.getParameter("operate");

        // 如果为空，则设置为index，然后会调用index方法
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }


        try {
            // public Method[] getDeclaredMethods()
            // 返回类中（类自身）所有的实例方法，包含public、protected和private方法。
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();

            // 循环遍历方法
            for (Method method : methods) {
                // 如果前端传递的参数方法 与 类中的方法一致则执行
                if (operate.equals(method.getName())) {
                    // --------------------------------- 统一获取前端传递的参数 ----------------------------------------
                    // 统一获取请求参数，获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();

                    // parameterValues 用来存放 获取到的每个参数的值
                    Object[] parameterValues = new Object[parameters.length];

                    // 遍历
                    for (int i = 0; i < parameters.length; i++) {
                        // 获取一个参数
                        Parameter parameter = parameters[i];
                        // 获取参数名
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            // 从请求中获取对应的参数值
                            String parameterValue = request.getParameter(parameterName);

                            // 获取 参数类型
                            String typeName = parameter.getType().getName();

                            // 获取Object型的参数值
                            Object parameterObj = parameterValue;

                            // 如果参数不为空
                            if (parameterObj != null) {
                                // 如果 参数类型 是Integer类型，就将参数值转为int型
                                if ("java.lang.Integer".equals(typeName)) {
                                    // Integer.parseInt()：将()内的String类型字符串转化为int类型
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            // 存储获取的参数
                            parameterValues[i] = parameterObj;
                        }
                    }

                    // ----------------------------------------------------- Controller组件中方法调用 -----------------------------------------------------
                    method.setAccessible(true);
                    // public Object invoke(Object obj, Object… args)
                    // 组件中的方法调用，方法对象.invoke(对象,方法参数)
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    // ----------------------------------------------------- 视图处理 -----------------------------------------------------
                    // 根据执行方法的返回值，做出对应的重定向或页面定位
                    String methodReturnStr = (String) returnObj;
                    // 如果获取的参数是redirect:开头的（比如：redirect:fruit.do）
                    if (methodReturnStr.startsWith("redirect:")) {        //比如：  redirect:fruit.do
                        // 截取redirect:（比如：剩下fruit.do）
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        // 重定向到对应页面
                        response.sendRedirect(redirectStr);
                    } else {
                        // 如果不是redirect:开头的，根据传递的值使用模板语法找到页面（比如：  "edit"）
                        super.processTemplate(methodReturnStr, request, response);    // 比如：  "edit"
                    }
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
