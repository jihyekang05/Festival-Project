package com.festivalP.demo.filter;

import com.festivalP.demo.domain.Admin;
import com.festivalP.demo.form.AuthInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class RoleFilter implements Filter {

    private static final String[] unAuthList = {"/","/member/**", "/allfestival"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =  (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        HttpSession session = httpServletRequest.getSession(false);

        try {
            if(!PatternMatchUtils.simpleMatch(unAuthList, requestURI)){
                AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

                if(authInfo.getState() !=2 ) {

                    System.out.println("");
                    System.out.println("#### 관리자가 아니야!!");
                    System.out.println("#### session is null!!!");
                    System.out.println("### rerquestURI: " + requestURI);
                    httpServletResponse.sendRedirect("/");
                    return;
                }
            }
            System.out.println("########## FILTER! ");

        } catch(Exception e){

        }

        chain.doFilter(request, response);

    }
}
