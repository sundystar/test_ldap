package com.sly.demo.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.sly.demo.entity.Person;

/** 
 * 这个类的作用是将ldap中的属性转化为实体类的属性值， 
 * 在查询信息的时候会用到 
 */  
public class PersonAttributeMapper implements AttributesMapper{  
  
    public Object mapFromAttributes(Attributes attr) throws NamingException {  
        Person person = new Person();  
        person.setSn((String)attr.get("sn").get());  
        person.setCn((String)attr.get("cn").get());  
          
        if (attr.get("userPassword") != null) {  
            person.setUserPassword((String)attr.get("userPassword").get());  
        }  
        if (attr.get("telephoneNumber") != null) {  
            person.setTelephoneNumber((String)attr.get("telephoneNumber").get());  
        }  
        if (attr.get("seeAlso") != null) {  
            person.setSeeAlso((String)attr.get("seeAlso").get());  
        }  
        if (attr.get("description") != null) {  
            person.setDescription((String)attr.get("description").get());  
        }  
        return person;  
    }  
  
}