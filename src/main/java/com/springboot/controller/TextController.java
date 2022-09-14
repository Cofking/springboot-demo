package com.springboot.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 */
@Controller
public class TextController {

    @GetMapping("/text")
    public ModelAndView text01(){
        ModelAndView mod=new ModelAndView();
        Map<String,String> map=new HashMap<>();
        for(int i=1;i<10;i++){
            map.put("name:"+i,"age:"+(10-i));
        }
        mod.addObject("map",map);
        A a = new A();
        a.setName("张三");
        a.setAge(18);
        mod.addObject("Penson",a);
        mod.setViewName("text01");
        System.out.println("GetMapping");
        return mod;
    }
    @GetMapping("/rest")
    @ResponseBody
    public String rest1(){

        return "GetMapping";
    }
    @PostMapping("/rest")
    @ResponseBody
    public String rest2(){
        return "PostMapping";
    }
    @DeleteMapping("/rest")
    @ResponseBody
    public String rest3(){

        return "Delete";
    }
    @PutMapping("/rest")
    @ResponseBody
    public String rest4(){
        return "PutMapping";
    }

    @ResponseBody
    @GetMapping("/car/{name}/flag/{age}/{address}")
    public Map<String,Object> path(@PathVariable("name") String name,
                                   @PathVariable("age") String age,
                                   @PathVariable("address") String address,
//                                  使用map接收 可以不指定变量参数 直接接收全部变量存入 map 中
                                   @PathVariable Map<String,String> pv,
                                   //获取请求头中的信息
                                   @RequestHeader(value = "User-Agent",required = false) String UserAgent,
//                                  使用map接收 可以不指定变量参数 直接接收全部变量存入 map 中
                                   @RequestHeader Map<String,String> pv1,

                                   @RequestParam(value = "sex",required = false) List<String> sex,
                                   @RequestParam(value = "habbit",required = false) String habbit,
//                                  使用map接收 可以不指定变量参数 直接接收全部变量存入 map 中
                                   //  接收多个参数的时候 map 只能接收一个   需要使用 MultiValueMap接收所有参数
                                   @RequestParam MultiValueMap<String,String> pv2,

                                   @CookieValue(value = "JSESSIONID",required = false) String JSESSIONID
                                   //获得 指定 cookie 对象
//                                   @CookieValue(value = "JSESSIONID") Cookie cookie
                                   ){
        Map<String,Object> map= new HashMap<>();
//        map.put("pv",pv);
//        map.put("pv1",pv1);
//        map.put("name",name);
//        map.put("age",age);
//        map.put("address",address);
//        map.put("UserAgent",UserAgent);

//        map.put("pv2",pv2);
//        map.put("sex",sex);
//        map.put("habbit",habbit);
//        Cookie cookie1=new Cookie("JSESSIONID","JSESSIONID");
        map.put("JSESSIONID",JSESSIONID);
//        System.out.println(cookie.getName()+"|"+cookie.getValue());
        return map;
    }
    @ResponseBody
    @PostMapping("/save")
    public Map postMeth(
            //获取请求体中所有的参数 form表单中的参数。
            @RequestBody String cont){
        Map<String,Object> map= new HashMap<>();
        map.put("cont",cont);
        return map;
    }

    /***
     *  /boss/boss1;age=12/emp2;age=11
     **/
    @ResponseBody
    @RequestMapping("/boss/{bossID}/{employeeID}")
    public Map boss(
            //矩阵变量  当cookie 禁用时可以使用矩阵变量传递jsessionid 等参数。
            // pathVar 指定 哪个路径变量下的矩阵参数
            //矩阵变量(@MatrixVariabl)  必须绑定  路径变量(@PathVariable)   使用
            @MatrixVariable(value = "age",pathVar = "bossID") String bossage,
                        @MatrixVariable(value = "age",pathVar = "employeeID") String empage,
                        @PathVariable("bossID") String bossid,
                        @PathVariable("employeeID") String empid
    ){
        Map<String,Object> map= new HashMap<>();
        map.put("bossage",bossage);
        map.put("empage",empage);
        map.put("bossid",bossid);
        map.put("empid",empid);
        return map;
    }














}

@Data
class A{
    public String name;
    public int age;
}
