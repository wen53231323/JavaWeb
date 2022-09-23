package Servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
//@WebServlet(urlPatterns = "/Servlet生命周期",loadOnStartup = 1)
public class Servlet_3_生命周期 implements Servlet {
    // 构造方法
    public Servlet_3_生命周期(){
        System.out.println("Servlet正在实例化");
    }
    // init()方法：初始化方法，在Servlet被创建时执行，只执行一次
    // init()方法调用时机：默认情况下，Servlet被第一次访问时调用
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet正在初始化");
    }

    //获取ServletConfig对象
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service()方法：提供服务方法，每次Servlet被访问，都会调用该方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet正在服务");
    }

    //该方法用来返回Servlet的相关信息，没有什么太大的用处，一般我们返回一个空字符串即可
    @Override
    public String getServletInfo() {
        return null;
    }

    //destroy()：当Servlet被销毁时，调用该方法。在内存释放或服务器关闭时销毁Servlet，只调用一次
    @Override
    public void destroy() {

    }
}