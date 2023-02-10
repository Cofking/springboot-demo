package com.springboot.converter;

import com.springboot.entity.A;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 自定义 converter
 */
public class DIYMessageConverter implements HttpMessageConverter<A> {

    /**
     * 能否将clazz 类型数据 读成 mediaType 类型   （读取）
     *
     * @param clazz
     * @param mediaType
     * @return
     */
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    /**
     * 能不能 将 clazz 类型 写成 mediaType  （输出）
     *
     * @param clazz
     * @param mediaType
     * @return
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        // 类型如果是 A 就写
        return clazz.isAssignableFrom(A.class);
    }

    /**
     * 服务器要统计所有 MessageConverter 能写出哪些内容的类型
     * <p>
     * 也就是 需要返回服务器一个 内容类型 供服务器 使用   application/x-guigu
     *
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        //  MediaType.parseMediaTypes() 媒体自定义类型
        return MediaType.parseMediaTypes("application/x-guigu");
    }

    /**
     * 读
     *
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    public A read(Class<? extends A> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 将读到的数据写出
     *
     * @param a
     * @param contentType
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    public void write(A a, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        //自定义协议数据写出
        String data = a.getName() + ";" + a.getAge() + ";";

        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());

    }
}
