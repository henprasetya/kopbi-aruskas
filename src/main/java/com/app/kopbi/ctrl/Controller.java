package com.app.kopbi.ctrl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.kopbi.dao.TransaksiKasDAO;
import com.app.kopbi.model.ParamTransaksiKas;
import com.app.kopbi.model.ResponseData;
import com.app.kopbi.model.TransaksiKas;
import com.app.kopbi.model.UserPrincipal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.env.Environment;

@RestController
public class Controller extends AbstractResource{

    @Autowired
    TransaksiKasDAO dao;
    
    @Autowired
    Environment env;

    @RequestMapping("/connect")
    public boolean connect() {
        return true;
    }

    @RequestMapping("/getobject-kas")
    public TransaksiKas getObject() {
        return new TransaksiKas();
    }
    
    @RequestMapping("/getobject-parameter")
    public ParamTransaksiKas getObjectParameter() {
        return new ParamTransaksiKas();
    }

    
    @RequestMapping("/import")
    public boolean importPinjaman(){
        return dao.importFile(env.getProperty("data.split"));
    }
    
    @RequestMapping(value = "/list-kas", produces = "application/json", method = RequestMethod.POST)
    public ResponseData listBy(@RequestBody String param) {
    	Gson gsn = new Gson();
    	Map<String, Object> filter = gsn.fromJson(param, new TypeToken<Map<String, Object>>() {
        }.getType());
    	return dao.list(filter);
    }

    @RequestMapping(value = "/post-kas", produces = "application/json", method = RequestMethod.POST)
    public Map postPinjaman(@RequestBody String param, HttpServletRequest servlet) {
    	UserPrincipal up = (UserPrincipal) servlet.getAttribute("userPrincipal");
        Gson gsn = new Gson();
        TransaksiKas p = gsn.fromJson(param, new TypeToken<TransaksiKas>() {
        }.getType());
        if (p.getId() == null) {
			p.setCreatedBy(up.getId());
			p.setCreatedDate(LocalDateTime.now());
		}else {
			p.setUpdatedBy(up.getId());
			p.setUpdatedDate(LocalDateTime.now());
		}
        boolean success = dao.saveTransaksiKas(p);
        
        Map map = new HashMap();
        map.put("success", success);
        return map;
    }
}
