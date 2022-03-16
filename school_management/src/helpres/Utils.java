/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpres;

/**
 *
 * @author hocin
 */
public class Utils {
    
    public static java.sql.Date getSqlDate(java.util.Date utilDate){
        return new java.sql.Date (utilDate.getTime());
    }
    
}
