/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2006 Zimbra, Inc.
 * 
 * The contents of this file are subject to the Yahoo! Public License
 * Version 1.0 ("License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 * http://www.zimbra.com/license.
 * 
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Factory that creates and manages instances of {@link Log}.
 * 
 * @author bburtin
 */
public class LogFactory {

    private static Map<Class, Log> sLogsByClass = new HashMap<Class, Log>();
    private static Map<String, Log> sLogsByName = new HashMap<String, Log>();
    
    public static Log getLog(Class clazz) {
        Log log = null;
        
        synchronized (sLogsByClass) {
            log = sLogsByClass.get(clazz);
            if (log == null) {
                Logger log4jLogger = Logger.getLogger(clazz);
                log = new Log(log4jLogger);
                sLogsByClass.put(clazz, log);
            }
        }
        
        return log;
    }
    
    public static Log getLog(String name) {
        Log log = null;
        
        synchronized (sLogsByName) {
            log = sLogsByName.get(name);
            if (log == null) {
                Logger log4jLogger = Logger.getLogger(name);
                log = new Log(log4jLogger);
                sLogsByName.put(name, log);
            }
        }
        
        return log;
    }
    
}
