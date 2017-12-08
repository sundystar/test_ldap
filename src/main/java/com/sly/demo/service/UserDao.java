package com.sly.demo.service;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import com.sly.demo.entity.User;

public class UserDao {

    private LdapTemplate ldapTemplate;

    public void add(String rdn,User user){
        Attributes attrs = new BasicAttributes();
        Attribute ocAttr = new BasicAttribute("objectClass");
        ocAttr.add("top");
        ocAttr.add("organizationalunit");
        attrs.put(ocAttr);
        Attribute snAttr = new BasicAttribute("sn");
        snAttr.add(user.getName());
        attrs.put(snAttr);
        this.ldapTemplate.bind(rdn, null, attrs);
    }
 
    public void delete(String rdn){
        this.ldapTemplate.unbind(rdn);
    }
 
    public void modifyRDN(String oRDN,String nRDN){
        this.ldapTemplate.rename(oRDN, nRDN);
    }
 
    public void modfiyAttrs(String rdn,User user){
        Attribute attr = new BasicAttribute("sn");
        attr.add(user.getName());
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        this.ldapTemplate.modifyAttributes(rdn, new ModificationItem[]{item});
    }
 
    public User find(String rdn) {
        return (User)this.ldapTemplate.lookup(rdn, new AttributesMapper(){

            public Object mapFromAttributes(Attributes attributes)
                    throws NamingException {
                User user = new User();
                if(attributes!=null){
                    Attribute idAttr = attributes.get("uid");
                    if(idAttr!=null){
                        user.setId((String)idAttr.get());
                    }
                    Attribute nameAttr = attributes.get("sn");
                    if(nameAttr!=null){
                        user.setName((String)nameAttr.get());
                    }
                }
                return user;
            }
        });
    }
 
    public List<User> search(String rdn,String fileter) {
        return this.ldapTemplate.search(rdn, fileter, 
                new AttributesMapper(){

                    public Object mapFromAttributes(Attributes attributes)
                            throws NamingException {
                        User user = new User();
                        if(attributes!=null){
                            Attribute idAttr = attributes.get("uid");
                            if(idAttr!=null){
                                user.setId((String)idAttr.get());
                            }
                            Attribute nameAttr = attributes.get("sn");
                            if(nameAttr!=null){
                                user.setName((String)nameAttr.get());
                            }
                        }
                        return user;
                    }
   
                });
        }
 
    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
 
    public void sd(){
    	System.err.println("ghjghj");
    }
    
}