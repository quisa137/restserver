package com.jindata.restserver.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


/**
 * 부모 클래스의 동작은 로그인이 필요한 리소스에 요청을 날리면 그 요청을 저장하고 있다가 로그인 절차가 성공하면
 * 저장한 요청을 처리하는 핸들러이다
 * @author SGcom
 *
 */
public class SigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        //로그인 시간 업데이트
        
        //토큰 생성 후 전송하기
        
        //컨텐츠 별 출력전송
        String accept = request.getHeader("accept"); 
        if( StringUtils.indexOf(accept, "html") > -1 ) { 
            super.onAuthenticationSuccess(request, response, authentication); 
        } else if( StringUtils.indexOf(accept, "xml") > -1 ) { 
            response.setContentType("application/xml"); 
            response.setCharacterEncoding("utf-8"); 
            String data = StringUtils.join(
                new String[] { 
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>", 
                    "<response>", 
                    "<error>false</error>", 
                    "<message>로그인하였습니다.</message>", 
                    "</response>"
                }); 
            PrintWriter out = response.getWriter(); 
            out.print(data); 
            out.flush(); 
            out.close(); 
        } else if( StringUtils.indexOf(accept, "json") > -1 ) { 
            response.setContentType("application/json"); 
            response.setCharacterEncoding("utf-8"); 
            String data = StringUtils.join(
                new String[] { 
                    " { \"response\" : {", 
                    " \"error\" : false , ", 
                    " \"message\" : \"로그인하였습니다.\" ", 
                    "} } " 
                }); 
            PrintWriter out = response.getWriter(); 
            out.print(data); 
            out.flush(); 
            out.close(); 
        }

    }
}
