/*
 *  Copyright 2013 Ivan Molodetskikh.
 *  
 *  This file is part of UnexpectedFishing.
 *
 *  UnexpectedFishing is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnexpectedFishing is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnexpectedFishing.  If not, see <http://www.gnu.org/licenses/>.
 */
package unexpectedfishing;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class UnexpectedFishingReflectionCache {
    public boolean available  = false;
    public boolean compatible = false;
    public HashMap classes    = new HashMap();
    public HashMap methods    = new HashMap();
    public HashMap fields     = new HashMap();
    
    public Object getFieldValue( String name, Object obj )
    {
        try
        {
            if ( fields.containsKey( name ) )
            {
                Field field = ( Field ) fields.get( name );
                return field.get( obj );
            }
            
            UnexpectedFishingMain.log( "No such field: " + name );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            UnexpectedFishingMain.log( "Failed to get a value of field: " + name );
        }
        
        return null;
    }
    
    public boolean setFieldValue( Object obj, String name, Object value )
    {
        try
        {
            if ( fields.containsKey( name ) )
            {
                Field field = ( Field ) fields.get( name );
                field.set( obj, value );
                
                return true;
            }
            
            UnexpectedFishingMain.log( "No such field: " + name );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            UnexpectedFishingMain.log( "Failed to set a value of field: " + name );
        }
        
        return false;
    }
    
    public Object invokeMethod( Object obj, String name, Object... args )
    {
        try
        {
            if ( methods.containsKey( name ) )
            {
                Method method = ( Method ) methods.get( name );
                
                if ( args != null )
                    return method.invoke( obj, args );
                
                return method.invoke( obj, new Object[0] );
            }
            
            UnexpectedFishingMain.log( "No such method: " + name );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            UnexpectedFishingMain.log( "Failed to invoke method: " + name );
        }
        
        return null;
    }
    
    public Object invokeStaticMethod( String className, String name, Object... args )
    {
        if ( classes.containsKey( className ) )
        {
            Class clazz = ( Class ) classes.get( className );
            
            return invokeMethod( clazz, name, args );
        }
        
        return null;
    }
    
    public boolean isInstance( String className, Object obj )
    {
        if ( classes.containsKey( className ) )
        {
            Class clazz = ( Class ) classes.get( className );
            return clazz.isInstance( obj );
        }
        
        return false;
    }
    
    public void storeClass( String name, Class clazz )
    {
        classes.put( name, clazz );
    }
    
    public void storeMethod( String name, Method method )
    {
        methods.put( name, method );
    }
    
    public void storeField( String name, Field field )
    {
        fields.put( name, field );
    }
}
