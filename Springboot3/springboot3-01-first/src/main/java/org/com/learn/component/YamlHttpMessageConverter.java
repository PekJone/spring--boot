package org.com.learn.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.com.learn.bean.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-10  11:15
 */
//一个消息转换器  专门处理yaml文件处理，用来处理yaml格式数据
    //所有的消息转换器 必须时间HttpMessageConverter接口 并实现重写方法
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter {

   private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));


    //将媒体类型text/yaml和消息转换器进行绑定  通过构造方法绑定

    public YamlHttpMessageConverter(){
        super(new MediaType("text","yaml", Charset.forName("UTF-8")));
    }
    /**
     * 这个方法用来指定此消息转换器适合哪种类型的对象
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class clazz) {
        //表示只有user类型的对象才能够使用该消息转换器进行转换
        return User.class.isAssignableFrom(clazz);
    }

    //这个是将yaml格式的字符串转换成java对象
    //@RequestBody
    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
    //这个是将java对象转换成yaml格式的字符串
    //@RequestBody
    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
           objectMapper.writeValue(outputMessage.getBody(),o);
    }
}
