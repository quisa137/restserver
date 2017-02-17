package com.jindata.restserver;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            String webappDir = "target/ROOT.war";
            Tomcat t = new Tomcat();
            try {
                //Connector에서 접속 포트를 설정 가능하다.
                Connector c = t.getConnector();
                c.setURIEncoding("UTF-8");
                c.setPort(8001);
                
                t.setBaseDir("target/ROOT");
                
                //접속할 호스트를 만들어 준다.
                StandardHost host = (StandardHost)t.getHost();
                host.setName("localhost"); //localhost 대신에 도메인 명을 붙일 수 있다.
                host.setAppBase("");
                host.setCopyXML(true);
                host.setAutoDeploy(true);
                host.setUnpackWARs(true);
                host.setDeployOnStartup(true);
                host.setErrorReportValveClass("com.jindata.restserver.ErrorReport");//org.apache.catalina.Valve를 구현한 오류보고서 클래스, JSON 형태로 변환
                
                //위에서 만든 호스트의 루트에 웹어플리케이션(war 파일)을 붙인다.
                //컨텍스트 패스만 다르면 어떤 어플리케이션이라도 붙일 수 있다.
                //Context 설정은 WEB-INF/context.xml에서 하는 것이 더 좋다.
                t.addWebapp(host,"", new File(webappDir).getAbsolutePath());
                
                t.start();
                t.getServer().await();
                
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            t.stop();
                        } catch (LifecycleException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }));
            } catch (LifecycleException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            
        }
    }
}
