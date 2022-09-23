package io;

// 根据id获取xml配置文件中的类
public interface BeanFactory {
    Object getBean(String id);
}
