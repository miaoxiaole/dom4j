package org.cherry.wechat.servlet;

/**
 * 核心请求处理类
 * @author:wuyue
 * @data:2016/5/15
 */

import org.cherry.wechat.util.SignUtil;
import service.CoreService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class CoreServlet {

    /**
     * 在服务器配置时，用于token校验
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        //如果校验成功，则对消息进行处理
        if (SignUtil.checkSignature(signature,timestamp,nonce)){
            //消息进行处理
            String respXml = CoreService.processRequest(request);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

    }
}