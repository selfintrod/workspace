package com.lingan.ksm.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class springConfig implements WebMvcConfigurer{
	
	/**
	 * 配置消息转换器，排除jackson依赖后使用fastjson，否则需要在converters中移除MappingJackson2HttpMessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		FastJsonHttpMessageConverter convert =new FastJsonHttpMessageConverter();
		FastJsonConfig config=new FastJsonConfig();
		//配置字符编码
		config.setCharset(Charset.forName("UTF-8"));
		//配置日期格式
		config.setDateFormat("yyyy-mm-dd");
		//自定义配置，如 'list为空时输出"  "而非null'等
        config.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty,
		SerializerFeature.PrettyFormat
		//SerializerFeature.WriteMapNullValue
		);
		convert.setFastJsonConfig(config);
		converters.add(convert);
	}

	
}
