/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.syntaxerror.myholylib;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

/**
 *
 * @author aml
 */
@WebService(serviceName = "login")
public class sesion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "registro")
    public String registro(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
