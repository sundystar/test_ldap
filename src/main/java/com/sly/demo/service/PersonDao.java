package com.sly.demo.service;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;

import com.sly.demo.entity.Person;
import com.sly.demo.mapper.PersonAttributeMapper;  
/** 
 * Description: 此类的作用是使用spring的 LdapTemplate完成对ldap的增删改查的操作 
 * Author:Ding Chengyun 
 */  
public class PersonDao {  
  
    //注入spring的LdapTemplate，此处在spring的配置文件中需要配置  
    private static LdapTemplate ldapTemplate;  
  
    public LdapTemplate getLdapTemplate() {  
        return ldapTemplate;  
    }  
    public void setLdapTemplate(LdapTemplate ldapTemplate) {  
        this.ldapTemplate = ldapTemplate;  
    }  
      
  
    /** 
     * 根据自定义的属性值查询person列表 
     * @param person 
     * @return 
     */  
    public static List<Person> getPersonList(  
            Person person) {  
        List<Person> list = new ArrayList<Person>();  
        //查询过滤条件  
        AndFilter andFilter = new AndFilter();  
        andFilter.and(new EqualsFilter("objectclass", "person"));  
          
          
        if (person.getCn() != null  
                && person.getCn().length() > 0) {  
            andFilter.and(new EqualsFilter("cn", person.getCn()));  
        }  
        if (person.getSn() != null  
                && person.getSn().length() > 0) {  
            andFilter.and(new EqualsFilter("sn", person.getSn()));  
        }  
  
        if (person.getDescription() != null  
                && person.getDescription().length() > 0) {  
            andFilter.and(new EqualsFilter("description", person.getDescription()));  
        }  
  
        if (person.getUserPassword() != null  
                && person.getUserPassword().length() > 0) {  
            andFilter.and(new EqualsFilter("userPassword", person.getUserPassword()));  
        }  
        if (person.getSeeAlso() != null  
                && person.getSeeAlso().length() > 0) {  
            andFilter.and(new EqualsFilter("seeAlso", person.getSeeAlso()));  
        }  
        if (person.getTelephoneNumber() != null   
                && person.getTelephoneNumber().length() > 0) {  
            andFilter.and(new EqualsFilter("telephoneNumber", person.getTelephoneNumber()));  
        }  
        //search是根据过滤条件进行查询，第一个参数是父节点的dn，可以为空，不为空时查询效率更高  
        list = ldapTemplate.search("", andFilter.encode(),  
                new PersonAttributeMapper());  
        return list;  
    }
    
    /**  
    24.      * 根据CN属性取得用户DN（当然你可以根据自己情况换成别的属性来操作）  
    25.      * @param cn  
    26.      * @return  
    27.      */    
        public String getDnForUser(String cn) {    
            EqualsFilter f = new EqualsFilter("cn", cn);    
            List result = ldapTemplate.search(DistinguishedName.EMPTY_PATH, f    
                     .toString(), new AbstractContextMapper() {    
                 protected Object doMapFromContext(DirContextOperations ctx) {    
                     return ctx.getNameInNamespace();    
                }    
             });    
            if (result.size() != 1) {    
                throw new RuntimeException("User not found or not unique");    
            }    
            return (String) result.get(0);    
        } 
        
    /**  
    42.  * 根据用户名密码验证  
    43.  * @param userDn  
    44.  * @param credentials  
    45.  * @return  
    46.  */    
        public boolean authenticate(String userDn, String credentials) {    
           DirContext ctx = null;    
             try {    
                 ctx = ldapTemplate.getContextSource().getContext(userDn,    
                         credentials);    
                return true;    
             } catch (Exception e) {    
               // Contextcreationfailed-authenticationdidnotsucceed    
                return false;    
             } finally {    
                 // ItisimperativethatthecreatedDirContextinstanceisalwaysclosed   
            	   LdapUtils.closeContext(ctx);    
             }    
       }
        
        public static void main(String[] args) {
        	
        	Person p = new Person();
        	p.setCn("liyangsuns");
        	
			List<Person> ps = getPersonList(p);
			for (Person person : ps) {
				System.err.println(person);
			}
		}
}  