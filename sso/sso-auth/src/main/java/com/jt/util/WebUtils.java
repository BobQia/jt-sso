package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WebUtils {
    public static void writeJsonToClient(HttpServletResponse response, Map<String,Object> map)
            throws IOException {
        //1设置响应数据的编码
        response.setCharacterEncoding("utf-8");
        //2告诉浏览器响应数据的内容类型以及编码
        response.setContentType("application/json;charset=utf-8");
        //3获取输出流对象
        PrintWriter out=response.getWriter();
        //4 将map转换为json数据
        String result=new ObjectMapper().writeValueAsString(map);
        //5 将数据响应到客户端
        out.println(result);
        out.flush();
    }
}
