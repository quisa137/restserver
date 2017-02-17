package com.jindata.restserver.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jindata.restserver.apis.beans.Information;

@Path("/")
@Produces({MediaType.APPLICATION_JSON})
public class InformationService {
    @GET
    public Information Get() {
        Information i = new Information();
        i.setName("RestServer");
        i.setVersion("0.0.1");
        i.setDescription("데이타 분석 솔루션 전용 웹 API 미들웨어");
        return i;        
    }
}
